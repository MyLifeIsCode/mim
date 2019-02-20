package com.mim.controller;

import com.mim.util.JsonUtils;
import com.mim.util.RedisKeyUtil;
import com.mim.vo.GroupMsgReq;
import com.mim.vo.P2pMsgReq;
import com.mim.service.MimRouteService;
import com.mim.thred.HttpThread;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.Executor;

@Controller
@RequestMapping(value = "/mimRoute")
public class MimRouteController {


    private static final String HTTP_PRE = "http://";
    private static final String P2P_MTHOD = "/mimMsg/sendP2PMsg";
    private static final String GROUP_METHOD = "/mimMsg/sendGroupMsg";

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Resource
    private MimRouteService mimRouteService;

    @Resource
    private Executor executor;

    @PostMapping(value = "/sendP2PMsg")
    public void sendP2PMsg(@RequestBody P2pMsgReq p2pMsgReq ){
        Long toUid = p2pMsgReq.getToUid();
        String serverInfo = redisTemplate.opsForValue().get(toUid.toString());
        String p2pJson = JsonUtils.objectToJson(p2pMsgReq);
        String url = HTTP_PRE + serverInfo + P2P_MTHOD;
        mimRouteService.sendPostHttp(url,p2pJson);
    }

    /**
     * 发送群组消息
     * @param groupMsgReq
     */
    @PostMapping(value = "/sendGroupMsg")
    public void sendGroupMsg(@RequestBody GroupMsgReq groupMsgReq ){
        Long groupId = groupMsgReq.getGroupId();
        Set<String> uidList = redisTemplate.opsForSet().members(RedisKeyUtil.getGroupUid(groupId));
        String groupJson = JsonUtils.objectToJson(groupMsgReq);
        uidList.forEach(uid->{
            String serverInfo = redisTemplate.opsForValue().get(uid);
            String url = HTTP_PRE + serverInfo + GROUP_METHOD;
            executor.execute(new HttpThread(url,groupJson));
//            mimRouteService.sendPostHttp(url,groupJson);
        });

    }


}

























