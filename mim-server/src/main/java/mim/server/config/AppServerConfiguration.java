package mim.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author qll
 * @create 2019-02-14 14:23
 * @desc 配置
 **/
@Component
public class AppConfiguration {

    @Value("${mim.server.port}")
    private int mimServerPort;

    @Value("${app.zk.addr}")
    private String zkAddr;

    @Value("${app.zk.root}")
    private String zkRoot;

}
