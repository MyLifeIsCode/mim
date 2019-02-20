package com.mim.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
@Documented
public @interface Handler {

    String cmd();//作用号

    Class reqClazz();

    Class resClazz();
}
