package com.zzhu.spring.tx.service.impl;

import com.zzhu.spring.tx.service.AsyncService;
import com.zzhu.spring.tx.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
//@DependsOn(value = "asyncServiceImpl")
public class TransactionServiceImpl implements TransactionService {
    public TransactionServiceImpl( ) {
        System.out.println("transaction实列化.....");
    }

    @Autowired
    private AsyncService asyncService;
    @Override
    @Transactional
    public void transactionWay() {

    }
}
