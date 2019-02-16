package mim.server.service;

import mim.server.annotation.Handeler;
import mim.server.vo.Session;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qll
 * @create 2019-02-16 18:37
 * @desc
 **/
@Component
public class MsgService extends ApplicationObjectSupport implements SmartInitializingSingleton {

    private Map<Long,Session> sessionMap = new ConcurrentHashMap<>();

    public void setSession(Long uid,Session session){
        sessionMap.put(uid,session);
    }

    public Session getSession(Long uid){
        return sessionMap.get(uid);
    }

    @Override
    public void afterSingletonsInstantiated() {
        ApplicationContext applicationContext = getApplicationContext();
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation("Handeler.class");
    }
}
