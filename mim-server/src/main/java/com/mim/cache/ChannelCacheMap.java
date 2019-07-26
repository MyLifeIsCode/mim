package com.mim.cache;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class ChannelCacheMap<K,V> extends HashMap<K, V> {
    private static final ChannelCacheMap<String, Channel> channelMap = new ChannelCacheMap<>();

    public static ChannelCacheMap getInstance(){
        return channelMap;
    }
    private HashMap<K,Long> expireMap = new HashMap<>();

    //保留时间
    private Long retentionTime = 1000 * 60 * 30L;

    public V put(K k,V v){
        Long expirtTime = retentionTime + System.currentTimeMillis();
        expirtPut(k,expirtTime);
        return super.put(k,v);
    }

    public V get(Object k){
        if(Objects.isNull(k))
            return null;
        if(chekExpire(k)){
            V v = super.get(k);
            return v;
        }else {
            removeExpireKey(k);
            return null;
        }

    }

    public void expirtPut(K k,Long expirtTime){
        expireMap.put(k,expirtTime);
    }

    public Boolean chekExpire(Object k){
        if(expireMap.containsKey(k)){
            return true;
        }
        Long time = expireMap.get(k);
        if(System.currentTimeMillis() < time){
            return true;
        }
        return false;
    }

    private void removeExpireKey(Object key){
        super.remove(key);
        expireMap.remove(key);
    }


    private void checkAllKeyExpire(){
        Set<Entry<K, V>> entries = super.entrySet();
        entries.forEach(entry ->{
            K key = entry.getKey();
            if(!chekExpire(key)){
                removeExpireKey(key);
            }
        });
    }
    public void clearExpireKey(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true){
                    checkAllKeyExpire();
                }
            }
        };
        runnable.run();
    }



}
