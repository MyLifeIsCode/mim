package com.common.util;

import com.common.inteceptor.HttpLogInteceptor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.BufferedSink;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class HttpUtils {

    public static void sendPostHttp(String url, String jsonStr){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new HttpLogInteceptor()).build();
        RequestBody requestBody = new RequestBody() {
            @Override
            public MediaType contentType() {
                return MediaType.parse("application/json; charset=utf-8");
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeUtf8(jsonStr);
            }
        };
        Request request = new Request.Builder().url(url).post(requestBody).build();
        okHttpClient.newCall(request).enqueue(new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                log.info("exec http fail ...");
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                log.info("exec http sucess ...");
            }
        });
    }

}
