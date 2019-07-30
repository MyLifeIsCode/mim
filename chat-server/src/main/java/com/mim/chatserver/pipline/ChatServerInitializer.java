package com.mim.chatserver.pipline;

import com.mim.chatserver.handler.in.HttpRequestHandler;
import com.mim.chatserver.handler.in.TextWebSocketFrameHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class ChatServerInitializer extends ChannelInitializer<Channel> {
    private final ChannelGroup group;

    public ChatServerInitializer(ChannelGroup group) {
        this.group = group;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new HttpServerCodec());//http 编解码器
        pipeline.addLast(new ChunkedWriteHandler());//继承自ChannelDuplexHandler表示对入站出站事件都进行日志记录。最佳实践：使用static修饰LoggingHandler实例，并在生产环境删除LoggingHandler。
        pipeline.addLast(new HttpObjectAggregator(64 * 1024));//用于合并Http请求头和请求体，主要在握手阶段进行处理；
        pipeline.addLast(new HttpRequestHandler("/ws"));
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));//按照websocket协议规范处理websocket升级握手、pingwebsocketframe、pongwebsocketframe、pongwebsocketframe和closewebsocketframe
        pipeline.addLast( new TextWebSocketFrameHandler(group));//处理文本数据
    }
}
