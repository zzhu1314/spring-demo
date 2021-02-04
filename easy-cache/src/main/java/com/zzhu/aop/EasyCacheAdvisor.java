package com.zzhu.aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;
import org.springframework.aop.support.AbstractPointcutAdvisor;

/**
 * easyCache的切面
 */
public class EasyCacheAdvisor extends AbstractBeanFactoryPointcutAdvisor{

    private Pointcut pointcut;


    public EasyCacheAdvisor() {
        this.pointcut = new EasyCachePointCut();
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

}
