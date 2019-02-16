package mim.server.handel;

import mim.server.annotation.Handler;
import mim.server.service.MsgService;
import mim.server.vo.Session;

/**
 * @author qll
 * @create 2019-02-16 18:44
 * @desc 登录操作
 **/
@Handler(cmd = "login")
public class LoginHandel extends AbstractHandler{

    private MsgService msgService;

    public void onLogin(Session session,Long uid){
        msgService.setSession(uid,session);
    }

    @Override
    public void onHandler() {
        System.out.println(2222);
    }
}
