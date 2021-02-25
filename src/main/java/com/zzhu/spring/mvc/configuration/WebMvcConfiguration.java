package com.zzhu.spring.mvc.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 注入WebMvcConfigurer一系列bean
 *
 * @EnableWebMvc  对各种类型的Mapping和Adapter进行实列话
 * 利用钩子方法 并对Mapping中的Interceptor赋值
 */
@EnableWebMvc
//@Configuration
public class WebMvcConfiguration {
}
