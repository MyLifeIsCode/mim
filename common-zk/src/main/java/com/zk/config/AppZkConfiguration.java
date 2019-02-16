package com.zk.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author qll
 * @create 2019-02-16 10:41
 * @desc 配置
 **/
@Data
@Configuration
public class AppZkConfiguration {

    @Value("${mim.zk.addr}")
    private String zkAddr;

    @Value("${app.zk.root}")
    private String zkRoot;

    @Value("${mim.zk.switch}")
    private boolean isSwitch;
}
