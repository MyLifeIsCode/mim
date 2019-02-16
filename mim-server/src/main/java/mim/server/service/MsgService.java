package mim.server.service;

import mim.server.vo.Session;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qll
 * @create 2019-02-16 18:37
 * @desc
 **/
@Component
public class MsgService {

    private Map<Long,Session> sessionMap = new ConcurrentHashMap<>();

    public void setSession(Long uid,Session session){
        sessionMap.put(uid,session);
    }

    public Session getSession(Long uid){
        return sessionMap.get(uid);
    }
}
