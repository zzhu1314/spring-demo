package com.zzhu.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EasyCacheConfiguration {
    @Bean
    public EasyCacheAdvisor easyCacheAdvisor(){
        EasyCacheAdvisor easyCacheAdvisor = new EasyCacheAdvisor();
        easyCacheAdvisor.setAdvice(easyCacheInterceptor());
        return easyCacheAdvisor;
    }
    @Bean
    public EasyCacheInterceptor easyCacheInterceptor(){
        return new EasyCacheInterceptor();
    }
}
