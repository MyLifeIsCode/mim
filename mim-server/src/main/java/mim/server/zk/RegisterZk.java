package mim.server.zk;

import com.common.util.IpUtil;
import mim.server.config.AppServerConfiguration;

import java.util.List;

/**
 * @author qll
 * @create 2019-02-16 14:27
 * @desc 注册服务到zk
 **/
public class RegisterZk implements Runnable{

    private ZKit zKit;

    private AppServerConfiguration appServerConfiguration;

    private String localIp;

    public RegisterZk(ZKit zKit, AppServerConfiguration appServerConfiguration, String localIp) {
        this.zKit = zKit;
        this.appServerConfiguration = appServerConfiguration;
        this.localIp = localIp;
    }

    @Override
    public void run() {
        zKit.createRootNode();
        String rootPath = appServerConfiguration.getZkRoot();
        int port = appServerConfiguration.getMimServerPort();
        String path = rootPath + "/" + localIp + ":"+port;
        zKit.createNode(path,path);

        List<String> allNode = zKit.getAllNode(rootPath);
        //节点变换更新缓存
        zKit.subscribeEvent(rootPath);
    }
}
