package com.zzhu.spring.mvc.tom;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.Set;

/**
 * 基于servlet3.0规范,tomcat的spi机制
 * tomcat启动时 会加载根路径下META-INF.services的javax.servlet.ServletContainerInitializer里的类
 * spring和springmvc容器的加载就是从spring-web包下面的META-INF.services启动的
 * 该类实现了ServletContainerInitializer接口
 * 收集@HandlesTypes中的类作为Set<Class<?>> set的参数
 */
@HandlesTypes(LoadServletService.class)
public class MyServletContainerInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        Iterator var4;
        if (set != null) {
            var4 = set.iterator();
            while (var4.hasNext()) {
                Class<?> clazz = (Class<?>) var4.next();
                if (!clazz.isInterface() && !Modifier.isAbstract(clazz.getModifiers()) && LoadServletService.class.isAssignableFrom(clazz)) {
                    try {
                        ((LoadServletService) clazz.newInstance()).loadOnstartup(servletContext);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
