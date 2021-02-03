package com.zzhu.core;

import com.zzhu.flow.HandlerData;
import com.zzhu.flow.first.FirstHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class CacheUtil implements InitializingBean {
    @Resource(name = "firstHandler")
    private HandlerData firstHandlerData;
    @Resource(name = "secondHandler")
    private HandlerData secondHandlerData;

    public <K, V> V getCache(K k) {
       return  firstHandlerData.handlerResult(k);
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        firstHandlerData.setHandlerData(secondHandlerData);
    }
}
