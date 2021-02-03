package com.zzhu.spring.tx.service.impl;

import com.zzhu.spring.tx.service.AsyncService;
import com.zzhu.spring.tx.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 异步方法提前暴露(发生循环依赖) 不会生成代理
 * 可以在注入bean上加@Lazy注解解决这个问题
 * 也可以让有@Transaction注解的bean早于@Async的bean实列化 也可以解决这个问题
 *
 * 问题的根本原因
 * 就是@Async的bean 在三级缓存时不会提前暴露，只能在bean的后置处理器时生成代理
 * 然而@Transaction注解能在三级缓存时提前暴露，在bean的后置处理器时就不会生成代理  所以在bean的后置处理器处理完成后 exposeBean==bean，就不会报错
 */
@Service
@DependsOn(value = "transactionServiceImpl")
public class AsyncServiceImpl implements AsyncService  {

    public AsyncServiceImpl( ) {
        System.out.println("async实列化.....");
    }

    @Autowired
    private TransactionService transactionService;
    @Override
    @Async
    public void asyncWay() {

    }
}
