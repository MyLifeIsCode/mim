package com.mim.distribute.lock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class RedisLockAspect {


    @Pointcut("@annotation(com.mim.distribute.lock.RedisLock)")
    public void redisLockPointCut() {
    }

    /*@Around("redisLockPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RedisLock redisLock = method.getAnnotation(RedisLock.class);
        String key = getKey(joinPoint,redisLock.key());
        boolean lock = redisLockService.lock(key);
        if(!lock){
            log.error("获取锁失败:{}",key);
            throw new BaseException(BUSY_SYSTEM);
        }

        Object result = null;
        Object[] args = joinPoint.getArgs();
        try {
            result = joinPoint.proceed(args);
        } catch (Throwable throwable) {
            throw new BaseException(ERROR);
        }
        redisLockService.unlock();
        return result;

    }*/

}
