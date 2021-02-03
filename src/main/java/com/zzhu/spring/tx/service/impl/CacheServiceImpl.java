package com.zzhu.spring.tx.service.impl;

import com.zzhu.spring.tx.service.CacheService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Cacheable 先查缓存，若缓存不存在 执行被代理方法，把返回结果作为value，调用put方法，加入缓存
 * @CachePut   执行被代理方法。将返回结果作为value，调用put方法，加入缓存
 * @CacheEvit(beforeInvocation）  清除缓存（前置清除或者后置清除），调用clear方法
 */
@Service
public class CacheServiceImpl implements CacheService {

    @Override
    @Cacheable(cacheNames = "redisCache", key = "#id")
    @CachePut(cacheNames = "redisCache")
    public String getName(String id) {
        System.out.println("执行被代理方法");
        String name = id + UUID.randomUUID();
        return name;
    }
}
