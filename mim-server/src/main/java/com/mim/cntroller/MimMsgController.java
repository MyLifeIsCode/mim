package com.mim.cntroller;

import com.mim.util.JsonUtils;
import com.mim.util.RedisKeyUtil;
import com.mim.vo.P2pMsgReq;
import com.mim.service.MsgService;
import com.mim.vo.GroupMsgReq;
import com.mim.vo.Session;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Set;

@Controller
@RequestMapping("/mimMsg")
public class MimMsgController {


    @Resource
    private MsgService msgService;


    @Resource
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 私聊
     * @param p2pMsgReq
     */
    @PostMapping(value = "/sendP2PMsg")
    public void sendP2PMsg(@RequestBody P2pMsgReq p2pMsgReq){
        Long toUid = p2pMsgReq.getToUid();
        Session session = msgService.getSession(toUid);
        session.sendMsg(JsonUtils.objectToJson(p2pMsgReq));
    }

    /**
     * 群聊
     * @param groupMsgReq
     */
    @PostMapping(value = "/sendGroupMsg")
    public void sendGroupMsg(@RequestBody GroupMsgReq groupMsgReq){
        Long groupId = groupMsgReq.getGroupId();
        Set<String> uidList = redisTemplate.opsForSet().members(RedisKeyUtil.getGroupUid(groupId));
        String groupJson = JsonUtils.objectToJson(groupMsgReq);
        uidList.forEach(uid->{
            Session session = msgService.getSession(Long.parseLong(uid));
            session.sendMsg(groupJson);
        });
    }

}
