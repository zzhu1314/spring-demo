package com.zzhu.aop;

import com.zzhu.annotation.EasyCache;
import com.zzhu.core.CacheUtil;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

public class EasyCacheInterceptor implements MethodInterceptor {
    @Autowired
    private CacheUtil cacheUtil;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        //获取目标对象
        Class<?> targetClass = getTargetClass(invocation.getThis());
        Method method = invocation.getMethod();
        Method specificMethod = AopUtils.getMostSpecificMethod(method, targetClass);
        EasyCache easyCache = specificMethod.getAnnotation(EasyCache.class);
        Object cache = cacheUtil.getCache(getKey(easyCache.key(), specificMethod, invocation.getArguments()));
        if (cache != null) {
            return cache;
        }
        return invocation.proceed();
    }


    public Class<?> getTargetClass(Object target) {
        return AopProxyUtils.ultimateTargetClass(target);
    }

    public String getKey(String key, Method method, Object[] args) {
        String[] parameterNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(method);
        String key1 = ElParser.getKey(key, parameterNames, args);
        Assert.isTrue(!StringUtils.isEmpty(key1), "缓存的key不能为空");
        return key1;
    }
}
