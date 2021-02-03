package com.zzhu.config;

import com.zzhu.cache.CaffeineCacheServiceImpl;
import com.zzhu.cache.GuavaCacheServiceImpl;
import com.zzhu.core.CacheUtil;
import com.zzhu.distrbutecache.RedisApi;
import com.zzhu.distrbutecache.RedisCacheServiceImpl;
import com.zzhu.flow.first.FirstHandler;
import com.zzhu.flow.second.SecondHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

/**
 * @Classname ConfigBeans
 * @Description TODO
 * @Author Jack
 * Date 2020/11/23 15:56
 * Version 1.0
 */
public class ConfigBeans {

    @Bean
    @ConditionalOnProperty(name = "spring.easycache.local.type", havingValue = "caffeine",
            matchIfMissing = true)
    public CaffeineCacheServiceImpl caffeineCacheService() {
        return new CaffeineCacheServiceImpl();
    }

    @Bean
    @ConditionalOnProperty(name = "spring.easycache.local.type", havingValue = "guava",
            matchIfMissing = false)
    public GuavaCacheServiceImpl guavaCacheService() {
        return new GuavaCacheServiceImpl();
    }

    @Bean
    public RedisApi redisApi() {
        return new RedisApi();
    }

    @Bean
    public CacheUtil cacheUtil() {
        return new CacheUtil();
    }

    @Bean
    @ConditionalOnProperty(name = "spring.easycache.distributed.type", havingValue = "redis",
            matchIfMissing = true)
    public RedisCacheServiceImpl redisCacheService() {
        return new RedisCacheServiceImpl();
    }

    @Bean(name = "firstHandler")
    public FirstHandler firstCacheHandler() {
        return new FirstHandler();
    }

    @Bean(name = "secondHandler")
    public SecondHandler secondCacheHandler() {
        return new SecondHandler();
    }

}
