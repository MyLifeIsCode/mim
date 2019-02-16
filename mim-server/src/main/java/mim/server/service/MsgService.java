package mim.server.service;

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

    private Map<Long,Session> sessionMap = new ConcurrentHashMap<>();

    private Map<String,AbstractHandler> handlerMap = new ConcurrentHashMap<>();
    public void setSession(Long uid,Session session){
        sessionMap.put(uid,session);
    }

    public Session getSession(Long uid){
        return sessionMap.get(uid);
    }

    @PostConstruct
    public void afterSingletonsInstantiated() {
        ApplicationContext applicationContext = getApplicationContext();
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Handler.class);
        for (Object handlerBean : beansWithAnnotation.values()){
            Class<? extends AbstractHandler> handlerClass = (Class<? extends AbstractHandler>) handlerBean.getClass();
            Handler handler = handlerClass.getAnnotation(Handler.class);
            String cmd = handler.cmd();
            handlerMap.put(cmd,(AbstractHandler) handlerBean);

        }

    }
}
