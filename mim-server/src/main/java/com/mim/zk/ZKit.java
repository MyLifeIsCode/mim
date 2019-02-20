package com.mim.zk;

import com.mim.config.AppServerConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qll
 * @create 2019-02-16 14:27
 * @desc zkutil
 **/
@Component
@Slf4j
public class ZKit {

    @Resource
    private AppServerConfiguration appServerConfiguration;

    @Resource
    private ZkClient zkClient;

    /**
     * 创建根节点
     */
    public void createRootNode(){
        if(!zkClient.exists(appServerConfiguration.getZkRoot())){
            zkClient.createPersistent(appServerConfiguration.getZkRoot());
        }
    }

    /**
     * 创建临时节点
     * @param path
     * @param data
     */
    public void createNode(String path,String data){
        if(!zkClient.exists(path)){
            zkClient.createEphemeral(path,data);
        }
    }
    /**
     * 创建临时节点数据
     * @param path
     * @param data
     */
    public void createNode(String path,Object data){
        if(!zkClient.exists(path)){
            zkClient.createEphemeral(path,data);
        }
    }

    /**
     * 获取某个路径下所有节点
     * @param path
     */
    public List<String> getAllNode(String path){
        List<String> children = zkClient.getChildren(path);
        return children;
    }

    public void subscribeEvent(String path){
        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> list) throws Exception {
                log.info("清除/更新本地缓存 parentPath=【{}】,currentChilds=【{}】", parentPath,list.toString());
            }
        });
    }
}
