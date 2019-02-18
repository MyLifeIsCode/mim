package com.route.service;

import com.common.util.HttpUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MimRouteService {


    public void sendPostHttp(String url,String jsonStr){
        HttpUtils.sendPostHttp(url,jsonStr);
    }
}
