package com.mim.vo;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qll
 * @create 2019-02-16 18:13
 * @desc
 **/
@Data
public class Session implements Serializable {

    private final Long uid;
    private final Channel channel;

    public Session(Long uid,Channel channel) {
        this.uid = uid;
        this.channel = channel;
    }

    public void sendMsg(String data){
        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(data);
        channel.writeAndFlush(textWebSocketFrame);
    }
}
