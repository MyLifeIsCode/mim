package mim.server.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

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
