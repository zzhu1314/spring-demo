package com.zzhu.flow;

public interface HandlerData {
    <T,K> T handlerResult(K k);

    void setHandlerData(HandlerData handlerData);
}
