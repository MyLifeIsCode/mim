package com.mim.config;


import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(SessionFactoryConfig.class) //保证在MyBatisConfig实例化之后再实例化该类
public class MapperScannerConfig {

    // mapper接口的扫描器
  /*  @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("cn.mim.domain");
        return mapperScannerConfigurer;
    }*/
}