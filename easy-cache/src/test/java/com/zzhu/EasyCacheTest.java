package com.zzhu;

import com.zzhu.core.CacheUtil;
import com.zzhu.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EasyCacheTest {
    @Autowired
    private CacheUtil cacheUtil;
    @Autowired
    private TestService testService;
    @Test
    public void test01()
    {
/*        cacheUtil.getCache("123");*/
        testService.getData("123441");
    }
}
