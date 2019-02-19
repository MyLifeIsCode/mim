package com.route.thred;

import com.common.util.HttpUtils;

public class HttpThread implements Runnable {
    private String url;
    private String jsonStr;

    public HttpThread(String url, String jsonStr) {
        this.url = url;
        this.jsonStr = jsonStr;
    }
    @Override
    public void run() {
        HttpUtils.sendPostHttp(url,jsonStr);
    }
}
