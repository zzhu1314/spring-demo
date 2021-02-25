package com.zzhu.spring.tx;

import com.zzhu.spring.mvc.service.impl.WebMvcServiceImpl;
import com.zzhu.spring.tx.service.CacheService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;

public class CacheTest {
    @Test
    public void cacheTest() throws IOException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CacheManagementBean.class);
        RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] keyb = "123".getBytes();
                byte[] valueb = SerializationUtils.serialize("您好");
                redisConnection.set(keyb, valueb);
                return null;
            }
        });
        CacheService bean = applicationContext.getBean(CacheService.class);
        bean.getName("999");
    }
}

