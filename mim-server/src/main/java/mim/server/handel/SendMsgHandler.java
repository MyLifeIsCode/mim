package mim.server.handel;

import com.common.util.JsonUtils;
import io.netty.channel.Channel;
import mim.server.annotation.Handler;
import mim.server.service.MsgService;
import mim.server.vo.LoginReq;
import mim.server.vo.LoginRes;
import mim.server.vo.PrivateChatRes;
import mim.server.vo.Session;

import javax.annotation.Resource;

@Handler(cmd = "privateChat",reqClazz = LoginReq.class,resClazz = LoginRes.class)
public class SendMsgHandler extends AbstractHandler{

    @Resource
    private MsgService msgService;

    @Override
    public void onHandler(Long uid, Channel channel) {
        Session session = msgService.getSession(uid);
        PrivateChatRes privateChatRes = new PrivateChatRes();
        privateChatRes.setMsg("123");
        privateChatRes.setToUid(uid);
        session.sendMsg(JsonUtils.objectToJson(privateChatRes));
    }
}
