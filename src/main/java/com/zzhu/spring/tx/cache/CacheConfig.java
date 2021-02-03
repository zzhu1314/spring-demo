package com.zzhu.spring.tx.cache;

import com.zzhu.spring.tx.service.CacheService;
import com.zzhu.spring.tx.service.impl.CacheServiceImpl;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CacheConfig {

    @Bean
    public RedisCache redisCache(RedisTemplate redisTemplate) {
        RedisCache redisCache = new RedisCache();
        redisCache.setName("redisCache");
        redisCache.setRedisTemplate(redisTemplate);
        return redisCache;
    }

    @Bean
    public CacheManager cacheManager(RedisCache redisCache) {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        List<Cache> cacheList = new ArrayList<>();
        cacheList.add(redisCache);
        simpleCacheManager.setCaches(cacheList);
        return simpleCacheManager;
    }
    @Bean
    public CacheService cacheService(){
        return new CacheServiceImpl();
    }
}
