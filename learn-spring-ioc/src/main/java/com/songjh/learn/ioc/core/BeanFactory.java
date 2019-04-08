package com.songjh.learn.ioc.core;

public interface BeanFactory {

    Object getBean(String name) throws Exception;

}
