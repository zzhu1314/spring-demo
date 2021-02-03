package com.zzhu.flow.second;

import com.zzhu.distrbutecache.CacheService;
import com.zzhu.flow.HandlerData;
import org.springframework.beans.factory.annotation.Autowired;

public class SecondHandler implements HandlerData {

    @Autowired
    private CacheService secondCacheService;
    @Override
    public <T, K> T handlerResult(K k) {
        return secondCacheService.get(k);
    }

    @Override
    public void setHandlerData(HandlerData handlerData) {

    }
}
