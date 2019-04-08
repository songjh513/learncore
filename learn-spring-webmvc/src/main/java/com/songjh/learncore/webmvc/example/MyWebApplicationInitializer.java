package com.songjh.learncore.webmvc.example;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created  by songjh on 2019-04-05 06:54.
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {
//        ServletRegistration.Dynamic registration = container.addServlet("dispatcher", new DispatcherServlet());
//        registration.setLoadOnStartup(1);
//        registration.addMapping("/example/*");
    }
}
