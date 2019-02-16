package com.zk.util;

import com.zk.config.AppZkConfiguration;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author qll
 * @create 2019-02-16 11:16
 * @desc
 **/
@Component
public class ZKit {
    @Resource
    private ZkClient zkClient;
    @Resource
    private AppZkConfiguration appZkConfiguration;

    /**
     * 创建根节点
     */
    public void createRootNode(){
        if(!zkClient.exists(appZkConfiguration.getZkRoot())){
            zkClient.createPersistent(appZkConfiguration.getZkRoot());
        }
    }
    /**
     * 创建临时节点
     * @param path
     * @param data
     */
    public void createNode(String path,Object data){
        zkClient.createEphemeral(path,data);
    }


}
