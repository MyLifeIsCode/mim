package mim.server.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author qll
 * @create 2019-02-14 14:23
 * @desc 配置
 **/
@Component
@NoArgsConstructor
@Data
public class AppServerConfiguration {

    @Value("${mim.server.port}")
    private int mimServerPort;

    @Value("${server.port}")
    private int serverPort;

    @Value("${app.zk.addr}")
    private String zkAddr;

    @Value("${app.zk.root}")
    private String zkRoot;

}
