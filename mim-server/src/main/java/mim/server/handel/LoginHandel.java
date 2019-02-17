package mim.server.handel;

import com.common.util.IpUtil;
import com.common.util.JsonUtils;
import io.netty.channel.Channel;
import mim.server.annotation.Handler;
import mim.server.config.AppServerConfiguration;
import mim.server.service.MsgService;
import mim.server.vo.LoginReq;
import mim.server.vo.LoginRes;
import mim.server.vo.Session;
import mim.server.zk.ZKit;
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
    private RedisTemplate<String,Object> redisTemplate;

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
        int mimServerPort = appServerConfiguration.getMimServerPort();
        String mimServerInfo = localIp + ":" + mimServerPort;
        redisTemplate.opsForValue().set(Long.toString(uid),mimServerInfo);
        onLogin(uid,channel);
    }
}
