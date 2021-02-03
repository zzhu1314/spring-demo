package com.zzhu.annotation;

import java.lang.annotation.*;

/**
 * 实现自定义一级缓存 二级缓存取值
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EasyCache {
    String key() default "";
}

