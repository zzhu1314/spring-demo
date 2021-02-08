package com.zzhu.spring.mvc.tom;

import javax.servlet.ServletContext;

public interface LoadServletService {

    void loadOnstartup(ServletContext servletContext);
}
