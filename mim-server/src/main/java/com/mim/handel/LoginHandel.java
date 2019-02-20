package com.mim.handel;

import com.mim.util.IpUtil;
import com.mim.util.JsonUtils;
import com.mim.annotation.Handler;
import com.mim.service.MsgService;
import com.mim.vo.LoginReq;
import io.netty.channel.Channel;
import com.mim.config.AppServerConfiguration;
import com.mim.vo.LoginRes;
import com.mim.vo.Session;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author qll
 * @create 2019-02-16 18:44
 * @desc 登录操作
 **/
@Handler(cmd = "login",reqClazz = LoginReq.class,resClazz = LoginRes.class)
public class LoginHandel extends AbstractHandler{

    @Resource
    private MsgService msgService;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Resource
    private AppServerConfiguration appServerConfiguration;


    public void onLogin(Long uid, Channel channel){
        Session session = new Session(uid,channel);
        msgService.setSession(uid,session);
        LoginRes loginRes = new LoginRes();
        loginRes.setLoginDate(new Date());
        loginRes.setMsg("登陆成功");
        loginRes.setUid(uid);
        String data = JsonUtils.objectToJson(loginRes);
        session.sendMsg(data);
    }

    @Override
    public void onHandler(Long uid, Channel channel) {
        String localIp = IpUtil.getLocalIp();
        int serverPort = appServerConfiguration.getServerPort();
        String mimServerInfo = localIp + ":" + serverPort;
        redisTemplate.opsForValue().set(Long.toString(uid),mimServerInfo);
        String s = redisTemplate.opsForValue().get(Long.toString(uid));
        onLogin(uid,channel);
    }
}
