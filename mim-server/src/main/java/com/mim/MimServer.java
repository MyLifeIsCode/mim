package com.mim;

import com.mim.config.AppServerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author qll
 * @create 2019-02-13 11:45
 * @desc 启动类
 **/

@SpringBootApplication
public class MimServer  implements CommandLineRunner {


    @Autowired
    private AppServerConfiguration appServerConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(MimServer.class,args);
    }

    @Override
    public void run(String... strings) throws Exception {//容器初始化完成后执行
        System.out.println(appServerConfiguration.getMimServerPort());
    }
}
