package com.zzhu.spring.tx.service.impl;

import com.zzhu.spring.tx.service.TxAndAsyncCirculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 事务与异步的循环依赖问题
 * 在循环依赖前提前
 * 由于 @Transactional会形成代理，所以在三级缓存时会提前暴露放入二级缓存，依赖注入返回的是一个代理对象，此时txAndAsyncCirculationService依赖对象并没有放入一级缓存中
 * 当bean依赖注入完成后，走到初始化时调用BeanPostProcessor的after方法，由于bean提前暴露 就不会再次走代理 返回的就是一个普通的未代理对象
 *  由于@Async注解会注入一个BeanPostProcessor 此时会走代理 ，返回一个代理对象，由于bean和暴露的bean不是同一个 且二级缓存中有循环依赖对象 所以后面会报错
 *  "Bean with name '" + beanName + "' has been injected into other beans [" +
 * 										StringUtils.collectionToCommaDelimitedString(actualDependentBeans) +
 * 										"] in its raw version as part of a circular reference, but has eventually been " +
 * 										"wrapped. This means that said other beans do not use the final version of the " +
 * 										"bean. This is often the result of over-eager type matching - consider using " +
 * 										"'getBeanNamesForType' with the 'allowEagerInit' flag turned off, for example.");
 *当把BeanFactory中的allowRawInjectionDespiteWrapping参数改为ture时 ，此时不会报错
 * 但一级缓存中的bean时一个异步代理bean
 * 循环依赖注入的对象是一个事务代理对象 不是同一个
 */
//@Service
public class TxAndAsyncCirculationServiceImpl implements TxAndAsyncCirculationService {
    @Autowired
    //@Lazy
    private TxAndAsyncCirculationService txAndAsyncCirculationService;

    @Override
    @Transactional
    @Async
    public void txAndAsync() {
        txAndAsyncCirculationService.test();;
        System.out.println("======循环依赖========");
    }

    @Override
    public void test() {
        System.out.println("====test====");
    }
}
