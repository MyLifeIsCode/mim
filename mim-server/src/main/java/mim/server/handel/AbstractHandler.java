package mim.server.handel;


import io.netty.channel.Channel;

/**
 * @author qll
 * @create 2019-02-16 19:32
 * @desc
 **/
public abstract class AbstractHandler {

    public void onHandler(Long uid, Channel channel){
        System.out.println(111);
    }
}
