package com.mim.distribute.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

import javax.annotation.Resource;
import java.util.Collections;

@Slf4j
public class RedisTool {

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    public static final String UNLOCK_LUA;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end ");
        UNLOCK_LUA = sb.toString();
    }

    @Resource
    private static RedisTemplate<String,String> redisTemplate;

    /**
     * 尝试获取分布式锁
//     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static String tryGetDistributedLock(String lockKey, String requestId, int expireTime) {
        /**
         * 重写redisTemplate的set方法
         * <p>
         * 命令 SET resource-name anystring NX EX max-lock-time 是一种在 Redis 中实现锁的简单方法。
         * <p>
         * 客户端执行以上的命令：
         * <p>
         * 如果服务器返回 OK ，那么这个客户端获得锁。
         * 如果服务器返回 NIL ，那么客户端获取锁失败，可以在稍后再重试。
         *
         * @param key     锁的Key
         * @param value   锁里面的值
         * @param seconds 过去时间（秒）
         * @return
         */
        return redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                Object nativeConnection = connection.getNativeConnection();
                String result = null;
                if (nativeConnection instanceof JedisCommands) {
                    result = ((JedisCommands) nativeConnection).set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
                }
                return result;
            }
        });
    }

    /**
     * 释放分布式锁
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseLock(String lockKey,String requestId) {
        // 释放锁的时候，有可能因为持锁之后方法执行时间大于锁的有效期，此时有可能已经被另外一个线程持有锁，所以不能直接删除
        try {
            // 使用lua脚本删除redis中匹配value的key，可以避免由于方法执行时间过长而redis锁自动过期失效的时候误删其他线程的锁
            // spring自带的执行脚本方法中，集群模式直接抛出不支持执行脚本的异常，所以只能拿到原redis的connection来执行脚本
            RedisCallback<Long> callback = (connection) -> {
                Object nativeConnection = connection.getNativeConnection();
                // 集群模式和单机模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
                // 集群模式
                if (nativeConnection instanceof JedisCluster) {
                    return (Long)((JedisCluster) nativeConnection).eval(UNLOCK_LUA, Collections.singletonList(lockKey), Collections.singletonList(requestId));
                }
                // 单机模式
                else if (nativeConnection instanceof Jedis) {
                    return (Long)((JedisCluster) nativeConnection).eval(UNLOCK_LUA, Collections.singletonList(lockKey), Collections.singletonList(requestId));
                }
                return 0L;
            };
            Long result = redisTemplate.execute(callback);

            return result != null && result > 0;
        } catch (Exception e) {
            log.error("release lock occured an exception", e);
        } finally {
            // 清除掉ThreadLocal中的数据，避免内存溢出
            //lockFlag.remove();
        }
        return false;
    }


}
