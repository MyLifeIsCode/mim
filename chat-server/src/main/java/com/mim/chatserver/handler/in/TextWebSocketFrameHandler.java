package com.mim.chatserver.handler.in;

import com.mim.chatserver.util.FastJsonUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private final ChannelGroup group;
    public TextWebSocketFrameHandler(ChannelGroup group) {
        this.group = group;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt == WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE){
            ctx.pipeline().remove(HttpRequestHandler.class);
            group.writeAndFlush(new TextWebSocketFrame("client" + ctx.channel() + "joined" + group.add(ctx.channel())));
        }else {
            super.userEventTriggered(ctx,evt);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
//        group.writeAndFlush(msg.retain());//发送给所有链接
        String content = msg.content().toString();
        FastJsonUtils.c
        ctx.channel().writeAndFlush(msg.retain());//发送给当前channel
//        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame("123456");
//        group.write(textWebSocketFrame);
//        group.flush();
    }
}





































