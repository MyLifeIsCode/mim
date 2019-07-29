package com.mim.service;

import com.mim.annotation.Handler;
import com.mim.cache.ChannelCacheMap;
import com.mim.handel.AbstractHandler;
import com.mim.vo.Session;
import io.netty.channel.Channel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qll
 * @create 2019-02-16 18:37
 * @desc
 **/
@Component
public class MsgService extends ApplicationObjectSupport  {

    //存放所有登录用户session
    private static ChannelCacheMap<Long, Session> channelCacheMap = new ChannelCacheMap<Long, Session>(30 * 60 * 1000L);
    private static Map<String,AbstractHandler> handlerMap = new ConcurrentHashMap<>();//存放handler
    private static Map<String,Class> reqMap = new ConcurrentHashMap<>();//存放请求Class
    private static Map<String,Class> resMap = new ConcurrentHashMap<>();//存放返回Class


    public void setSession(Long uid, Session session){
        channelCacheMap.put(uid,session);
    }

    public Session getSession(Long uid){
        return channelCacheMap.get(uid);
    }

    public ChannelCacheMap<Long, Session> getChannelCacheMap(){
        return channelCacheMap;
    }

    public Map<String,AbstractHandler> getHandlerMap(){
        return handlerMap;
    }

    public Map<String,Class> getReqMap(){
        return reqMap;
    }
    public Map<String,Class> getResMap(){
        return resMap;
    }

    @PostConstruct
    public void afterSingletonsInstantiated() {
        ApplicationContext applicationContext = getApplicationContext();
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Handler.class);
        for (Object handlerBean : beansWithAnnotation.values()){
            Class<? extends AbstractHandler> handlerClass = (Class<? extends AbstractHandler>) handlerBean.getClass();
            Handler handler = handlerClass.getAnnotation(Handler.class);
            String cmd = handler.cmd();
            Class reqClazz = handler.reqClazz();
            Class resClazz = handler.resClazz();
            handlerMap.put(cmd,(AbstractHandler) handlerBean);
            reqMap.put(cmd,reqClazz);
            reqMap.put(cmd,resClazz);
        }

    }
}
