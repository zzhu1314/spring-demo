package com.zzhu.distrbutecache;

public interface CacheService {
    <T,K> T get(K k);

    <K,V> V put(K k,V v);
}
