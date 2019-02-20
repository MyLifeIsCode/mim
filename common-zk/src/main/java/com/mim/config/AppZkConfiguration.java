package com.mim.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author qll
 * @create 2019-02-16 10:41
 * @desc 配置
 **/
@Data
@Configuration
public class AppZkConfiguration {

    @Value("${mim.mim.addr}")
    private String zkAddr;

    @Value("${app.mim.root}")
    private String zkRoot;

    @Value("${mim.mim.switch}")
    private boolean isSwitch;
}
