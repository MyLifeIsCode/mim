package com.route.controller;

import com.common.util.JsonUtils;
import com.common.vo.P2pMsgReq;
import com.route.service.MimRouteService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

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

    @PostMapping(value = "/sendMsg")
    public void sendMsg(@RequestBody P2pMsgReq p2pMsgReq ){
        Long toUid = p2pMsgReq.getToUid();
        String serverInfo = redisTemplate.opsForValue().get(toUid.toString());
        String p2pJson = JsonUtils.objectToJson(p2pMsgReq);
        String url = HTTP_PRE + serverInfo + P2P_MTHOD;
        mimRouteService.sendPostHttp(url,p2pJson);


    }
}
