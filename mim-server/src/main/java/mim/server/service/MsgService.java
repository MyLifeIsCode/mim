package mim.server.service;

import io.netty.channel.Channel;
import mim.server.annotation.Handler;
import mim.server.handel.AbstractHandler;
import mim.server.vo.Session;
import org.springframework.beans.factory.SmartInitializingSingleton;
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

    private static Map<Long,Session> sessionMap = new ConcurrentHashMap<>();//存放所有session
    private static Map<String,AbstractHandler> handlerMap = new ConcurrentHashMap<>();//存放handler
    private static Map<String,Class> reqMap = new ConcurrentHashMap<>();//存放请求Class
    private static Map<String,Class> resMap = new ConcurrentHashMap<>();//存放返回Class


    public void setSession(Long uid, Session session){
        sessionMap.put(uid,session);
    }

    public Session getSession(Long uid){
        return sessionMap.get(uid);
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
