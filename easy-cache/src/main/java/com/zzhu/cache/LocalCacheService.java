package com.zzhu.cache;

/**
 * 本地缓存
 */
public interface LocalCacheService {

    <T,K> T get(K k);

    <K,V> V put(K k,V v);
}
