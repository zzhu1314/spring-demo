package com.zzhu.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.util.SerializationUtils;

import java.time.Duration;

public class CaffeineCacheServiceImpl implements LocalCacheService, SmartInitializingSingleton, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private Cache<String ,byte[]> cache;

    @Override
    public <T, K> T get(K k) {
        final String keyf = k.toString();
        //byte[] key = keyf.getBytes();
        byte[] value = cache.getIfPresent(keyf);
        System.out.println("从一级缓存中获取数据 result="+SerializationUtils.deserialize(value));
        return (T) SerializationUtils.deserialize(value);
    }

    @Override
    public <K, V> V put(K k, V v) {
        final String keyf = k.toString();
        //byte[] key = keyf.getBytes();
        byte[] value = SerializationUtils.serialize(v);
        cache.put(keyf, value);
        return v;
    }

    /**
     * 初始化Caffeine
     */
    @Override
    public void afterSingletonsInstantiated() {
        Environment environment = applicationContext.getEnvironment();
        cache = Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofSeconds(Long.valueOf(environment.getProperty("spring.caffeineCache.expireAfterWrite"))))
                .maximumSize(Long.valueOf(environment.getProperty("spring.caffeineCache.maximumSize")))
                .build();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
