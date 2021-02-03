package com.zzhu.spring.tx;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("com.zzhu.spring.tx.cache")
@Configuration
@EnableCaching
public class CacheManagementBean {
}
