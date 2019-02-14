package mim.server.booter;

import mim.server.mserver.WSServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author qll
 * @create 2019-02-13 14:14
 * @desc
 **/
@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(contextRefreshedEvent.getApplicationContext().getParent() == null){
            try {
                //开启WebSocket服务
                WSServer.getInstance().start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
