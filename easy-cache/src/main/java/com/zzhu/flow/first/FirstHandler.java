package com.zzhu.flow.first;

import com.zzhu.cache.LocalCacheService;
import com.zzhu.distrbutecache.CacheService;
import com.zzhu.flow.HandlerData;
import org.springframework.beans.factory.annotation.Autowired;

public class FirstHandler implements HandlerData {
    @Autowired
    private LocalCacheService firstCacheService;

    private HandlerData secondHandlerData;

    @Override
    public <T, K> T handlerResult(K k) {
        Object firstValue = firstCacheService.get(k);
        if(firstValue==null){
            Object secondValue = secondHandlerData.handlerResult(k);
            if(secondValue!=null){
                firstCacheService.put(k,secondValue);
            }
            return (T) secondValue;
        }
        return (T) firstValue;
    }

    @Override
    public void setHandlerData(HandlerData secondHandlerData) {
        this.secondHandlerData = secondHandlerData;
    }

}
