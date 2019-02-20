package com.mim.config;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author qll
 * @create 2019-02-16 14:24
 * @desc bean
 **/
@Configuration
@EnableCaching
public class BeanConfig {

    @Resource
    private AppServerConfiguration appServerConfiguration;

    @Bean
    public ZkClient buildZKClient(){
        return new ZkClient(appServerConfiguration.getZkAddr(), 5000);
    }

}
