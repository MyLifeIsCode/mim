package mim.server.handler;

import com.common.util.JsonUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import mim.server.domain.TextData;

/**
 * @author qll
 * @create 2019-02-13 14:16
 * @desc
 **/
@Slf4j
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        log.info(evt.toString());
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    static public ChannelGroup clients =
            new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        Channel currentChannel = ctx.channel();
        //获取客户端传输过来的消息
        String content = msg.text();

        System.out.println("接收的数据：" + content);
        //1.获取客户端发送来的消息
        TextData textData = JsonUtils.jsonToPojo(content, TextData.class);
        log.info(textData.toString());
        log.info(ctx.channel().id().toString());
        /*

        DataContent dataContent = JsonUtils.jsonToPojo(content, DataContent.class);
        Integer action = dataContent.getAction();
        //2判断消息的类型，更具不同的类型来处理不同的业务
        if (action == MsgActionEnum.CONNECT.getType()) {
            //2.1当websocket 第一次open的时候 初始化channel 并把userid和channel进行绑定
            String senderId = dataContent.getMixinMsg().getSenderId();
            UserChannelRel.put(senderId, currentChannel);
        } else if (action == MsgActionEnum.CHAT.getType()) {
            //2.2聊天类型的消息
            MixinMsg mixinMsg = dataContent.getMixinMsg();
            String msgText = mixinMsg.getMsg();
            String recevierId = mixinMsg.getReceiverId();
            String senderId = mixinMsg.getSenderId();

            //保存消息到数据库，并且标记为未签收
            IChatMsgService chatMsgService = (IChatMsgService) SpringUtil.getBean("chatMsgServiceImpl");

            String msgId = chatMsgService.saveMsg(mixinMsg);
            mixinMsg.setMsgId(msgId);

            //构造发送的消息
            DataContent dataContentMsg = new DataContent();
            dataContentMsg.setMixinMsg(mixinMsg);
            //发送消息
            Channel recvchannel = UserChannelRel.get(recevierId);
            //从全局用户channel关系中获取接收方的channel
            if (recvchannel == null) {
                //TODD channel为空代表用户离线 推送消息
            } else {
                //当channel 不为空的时候 从ChannelGroup去查找channnel是否存在\
                Channel findChannel = clients.find(recvchannel.id());
                if (findChannel == null) {
                    //TODD channel为空代表用户离线 推送消息
                } else {
                    //用户在线
                    recvchannel.writeAndFlush(new TextWebSocketFrame(
                            JsonUtils.objectToJson(dataContentMsg)
                    ));
                }
            }
        } else if (action == MsgActionEnum.SIGNED.getType()) {
            //批量签收消息

        } else if (action == MsgActionEnum.KEEPALIVE.getType()) {
            //2.2心跳类型的消息
            System.out.println("收到【" + ctx.channel() + "】的心跳包！");
        }*/


        /*
        //群发
        TextWebSocketFrame tws = new TextWebSocketFrame(new Date().toString() + "--"
                + ctx.channel().id() + "===》" + content);
        for (Channel channel : clients) {
            channel.writeAndFlush(tws);
        }*/

//      下面这个方法 和上面的for循环 一致
//       clients.writeAndFlush(tws);

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //ChannelGroup会自动移除
        clients.remove(ctx.channel());
    }


    //异常处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
        clients.remove(ctx.channel());
    }
}
