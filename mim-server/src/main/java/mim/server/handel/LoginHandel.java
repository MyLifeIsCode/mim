package mim.server.handel;

import mim.server.annotation.Handeler;
import mim.server.service.MsgService;
import mim.server.vo.Session;

/**
 * @author qll
 * @create 2019-02-16 18:44
 * @desc 登录操作
 **/
@Handeler(cmd = "login")
public class LoginHandel {

    private MsgService msgService;

    public void onLogin(Session session,Long uid){
        msgService.setSession(uid,session);
    }
}
