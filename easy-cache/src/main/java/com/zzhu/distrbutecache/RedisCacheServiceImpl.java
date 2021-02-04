package com.zzhu.distrbutecache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;

public class RedisCacheServiceImpl implements CacheService {
    @Autowired
    private RedisApi redisApi;

    @Override
    public <T, K> T get(K k) {
        final String keyf = k.toString();
        byte[] key = keyf.getBytes();
        byte[] ifPresent = redisApi.get(key);
        System.out.println("从二级缓存中获取数据 result="+SerializationUtils.deserialize(ifPresent));
        return (T) SerializationUtils.deserialize(ifPresent);
    }

    @Override
    public <K, V> V put(K k, V v) {
        final String keyf = k.toString();
        byte[] keyb = keyf.getBytes();
        byte[] valueb = SerializationUtils.serialize((Serializable) v);
        redisApi.set(keyb, valueb);
        return v;
    }
}
