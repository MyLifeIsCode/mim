package com.common.inteceptor;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

@Slf4j
public class HttpLogInteceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long startTime = System.currentTimeMillis();
        Response response = chain.proceed(request);
        long endTime = System.currentTimeMillis();
        log.info("执行http时间------------" + (endTime - startTime));
        return response;
    }
}
