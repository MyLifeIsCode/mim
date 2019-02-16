package com.zk.thread;

import com.zk.config.AppZkConfiguration;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.zk.util.*;

/**
 * @author qll
 * @create 2019-02-16 11:28
 * @desc 注册服务
 **/
@Data
public class RegisterZk implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(RegisterZk.class);

    private AppZkConfiguration appZkConfiguration ;
    private ZKit zKit;
    private String ip;
    private int port;

    public RegisterZk(AppZkConfiguration appZkConfiguration, ZKit zKit, String ip, int port) {
        this.appZkConfiguration = appZkConfiguration;
        this.zKit = zKit;
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run() {
        zKit.createRootNode();
        //注册自己
        if(appZkConfiguration.isSwitch()){
            String rootPath = appZkConfiguration.getZkRoot();
            String serverData = "/ip-" + ip + ":" + port;
            String path = rootPath + serverData;
            zKit.createNode(path,serverData);
        }


    }
}
