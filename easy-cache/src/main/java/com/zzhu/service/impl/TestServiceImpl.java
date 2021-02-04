package com.zzhu.service.impl;

import com.zzhu.annotation.EasyCache;
import com.zzhu.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Override
    @EasyCache(key = "#id")
    public void getData(String id) {
        System.out.println("1234");
    }
}
