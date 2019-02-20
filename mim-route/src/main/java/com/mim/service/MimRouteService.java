package com.mim.service;

import com.mim.util.HttpUtils;
import org.springframework.stereotype.Service;

@Service
public class MimRouteService {


    public void sendPostHttp(String url,String jsonStr){
        HttpUtils.sendPostHttp(url,jsonStr);
    }
}
