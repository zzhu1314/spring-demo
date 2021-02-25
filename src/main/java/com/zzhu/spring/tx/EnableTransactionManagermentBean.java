package com.zzhu.spring.tx;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurationSelector;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;


/**
 * @EnableAspectJAutoProxy和@EnableTransactionManagement都会生成代理但同时只会有一个注入IOC容器
 * AopConfigUtils内部对APC_PRIORITY_LIST代理入口类进行了排序,谁在最后谁的优先级最高
 */
@Configuration
@ComponentScan(value = "com.zzhu.spring.tx")
@MapperScan(basePackages = "com.zzhu.spring.tx.mapper")
@EnableTransactionManagement //开启事务代理的入口
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableAsync
//@Import(TransactionManagementConfigurationSelector.class)     自己引入会报错  必须要有EnableTransactionManagement 注解
public class EnableTransactionManagermentBean {

    /**
     <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/cc-ipcc");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
        return dataSourceTransactionManager;
    }


    @Bean
    public TransactionTemplate transactionTemplate() {
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(platformTransactionManager());
        return transactionTemplate;
    }

    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        return threadPoolTaskExecutor;
    }
}
