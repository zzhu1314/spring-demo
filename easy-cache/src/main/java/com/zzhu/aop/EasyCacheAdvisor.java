package com.zzhu.aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;
import org.springframework.aop.support.AbstractPointcutAdvisor;

/**
 * easyCache的切面
 */
public class EasyCacheAdvisor extends AbstractPointcutAdvisor {

    private Pointcut pointcut;

    private Advice advice;

    public EasyCacheAdvisor() {
        this.pointcut = new EasyCachePointCut();
        this.advice = new EasyCacheInterceptor();
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }
}
