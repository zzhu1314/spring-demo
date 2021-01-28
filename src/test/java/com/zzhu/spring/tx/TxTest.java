package com.zzhu.spring.tx;


import com.zzhu.spring.tx.service.CommonService;
import com.zzhu.spring.tx.service.IStudentService;
import com.zzhu.spring.tx.service.TxAndAsyncCirculationService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TxTest {

    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(EnableTransactionManagermentBean.class);
        CommonService bean = applicationContext.getBean(CommonService.class);
        bean.transaction();
        bean.transaction();
    }

    @Test
    public void test02(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(EnableTransactionManagermentBean.class);
        TxAndAsyncCirculationService bean = applicationContext.getBean(TxAndAsyncCirculationService.class);
        bean.txAndAsync();

    }
}
