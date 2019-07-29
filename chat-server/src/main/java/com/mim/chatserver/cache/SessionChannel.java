package com.mim.chatserver.cache;

import com.mim.chatserver.chatsession.ChannelSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionChannel {
    public static final Map<Long, ChannelSession> channelMap = new ConcurrentHashMap<>();

    public static void addSessionChannel(Long uid,ChannelSession channelSession){
        channelMap.put(uid,channelSession);
    }
}
