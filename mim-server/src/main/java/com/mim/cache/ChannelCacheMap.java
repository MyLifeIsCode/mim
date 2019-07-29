package com.mim.cache;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

@Slf4j
public class ChannelCacheMap<K,V> extends HashMap<K, V> {

    //保留时间
    private Long retentionTime;

    public ChannelCacheMap(Long retentionTime){
        if(Objects.isNull(retentionTime)){
            this.retentionTime =  1000 * 60 * 30L;
        }else {
            this.retentionTime = retentionTime;
        }
    }


    private HashMap<K,Long> expireMap = new HashMap<>();



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


    private void checkAllKeyExpire(ChannelCacheMap<K,V> channelCacheMap){
        Set<Entry<K, V>> entries = channelCacheMap.entrySet();
        entries.forEach(entry ->{
            K key = entry.getKey();
            if(!chekExpire(key)){
                removeExpireKey(key);
            }
        });
    }
    public void clearExpireKey(ChannelCacheMap<K,V> channelCacheMap){
        while (true){
            checkAllKeyExpire(channelCacheMap);
            try {
                log.info("刷新缓存 {}",System.currentTimeMillis());
                Thread.sleep(6*1000);
            } catch (InterruptedException e) {
                log.error("刷新缓存失败");
            }
        }
    }


}
