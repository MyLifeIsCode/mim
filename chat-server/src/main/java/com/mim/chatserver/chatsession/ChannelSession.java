package com.mim.chatserver.chatsession;

import lombok.Data;

import java.io.Serializable;
import java.nio.channels.Channel;
@Data
public class ChannelSession implements Serializable {

    private Long uid;
    private Channel channel;


}
