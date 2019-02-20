package com.mim.handel;

import com.mim.util.JsonUtils;
import com.mim.annotation.Handler;
import com.mim.service.MsgService;
import com.mim.vo.LoginReq;
import com.mim.vo.LoginRes;
import io.netty.channel.Channel;
import com.mim.vo.PrivateChatRes;
import com.mim.vo.Session;

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
