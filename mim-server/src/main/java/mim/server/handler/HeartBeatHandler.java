package mim.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author qll
 * @create 2019-02-13 14:17
 * @desc
 **/
//处理心跳
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state();
            if (state == IdleState.READER_IDLE) {
                System.out.println("进入读空闲。。。");
            }else if(state == IdleState.WRITER_IDLE){
                System.out.println("进入写空闲。。。");
            }else if(state == IdleState.ALL_IDLE){
                //关闭无用的channel 以防资源浪费
                Channel channel = ctx.channel();
                channel.close();
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
