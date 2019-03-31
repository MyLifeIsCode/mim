package com.netty.server.handle;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 初始化
 */
public class EchoInitialzer extends ChannelInitializer<NioServerSocketChannel> {
    @Override
    protected void initChannel(NioServerSocketChannel nioServerSocketChannel) throws Exception {
        ChannelPipeline pipeline = nioServerSocketChannel.pipeline();
        //添加编码

        pipeline.addLast(new EchoServerHandler());

    }
}
