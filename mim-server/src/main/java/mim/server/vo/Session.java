package mim.server.vo;

import io.netty.channel.Channel;

import java.io.Serializable;

/**
 * @author qll
 * @create 2019-02-16 18:13
 * @desc
 **/
public class Session implements Serializable {

    private final Long uid;
    private final Channel channel;

    public Session(Long uid,Channel channel) {
        this.uid = uid;
        this.channel = channel;
    }
}
