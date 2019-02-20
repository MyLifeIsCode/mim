package com.mim.mserver;

/**
 * @author qll
 * @create 2019-02-13 14:14
 * @desc
 **/

import com.mim.util.IpUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import com.mim.config.AppServerConfiguration;
import com.mim.handler.WSServerInitialzer;
import com.mim.zk.RegisterZk;
import com.mim.zk.ZKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


/**
 * 考虑反射：
 * 　　由于在调用 SingletonHolder.instance 的时候，才会对单例进行初始化，而且通过反射，是不能从外部类获取内部类的属性的。
 * 　　所以这种形式，很好的避免了反射入侵。
 * 考虑多线程：
 * 　　由于静态内部类的特性，只有在其被第一次引用的时候才会被加载，所以可以保证其线程安全性。
 * 不需要传参的情况下 优先考虑静态内部类
 */
@Component
public class WSServer {

    Logger logger = LoggerFactory.getLogger(WSServer.class);
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ServerBootstrap server;
    private ChannelFuture future;


    @Resource
    private AppServerConfiguration appServerConfiguration;

    @Resource
    private ZKit zKit;

    private static class SingletionWSServer{
        static final WSServer instance = new WSServer();
    }

    public static WSServer getInstance(){
        return SingletionWSServer.instance;
    }

    public WSServer() {
        bossGroup = new NioEventLoopGroup();
        workerGroup =new NioEventLoopGroup();
        server = new ServerBootstrap();

        server.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WSServerInitialzer());//自定义初始化handler容器
    }

    @PostConstruct
    public void start(){
        //自定义端口
        int mimServerPort = appServerConfiguration.getMimServerPort();
//        int mimServerPort = 8888;
        this.future = server.bind(mimServerPort);
        logger.info( "{}启动成功！",mimServerPort);

        //注册到zk
        RegisterZk registerZk = new RegisterZk(zKit,appServerConfiguration,IpUtil.getLocalIp());
        Thread thread = new Thread(registerZk);
        thread.start();
    }

}
