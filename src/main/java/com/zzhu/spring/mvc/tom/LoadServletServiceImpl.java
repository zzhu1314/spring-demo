package com.zzhu.spring.mvc.tom;

import com.zzhu.spring.mvc.servlet.InitServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class LoadServletServiceImpl implements LoadServletService {
    @Override
    public void loadOnstartup(ServletContext servletContext) {
        ServletRegistration.Dynamic initServlet = servletContext.addServlet("initServlet", InitServlet.class);
        initServlet.setLoadOnStartup(1);
        initServlet.addMapping("/init");
    }
}
