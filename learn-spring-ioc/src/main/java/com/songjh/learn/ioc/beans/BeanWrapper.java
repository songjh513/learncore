package com.songjh.learn.ioc.beans;

import com.songjh.learn.ioc.aop.AopConfig;
import com.songjh.learn.ioc.aop.AopProxy;
import com.songjh.learn.ioc.core.FactoryBean;

/**
 * Created  by songjh on 2019-04-09 07:44.
 */
public class BeanWrapper extends FactoryBean {

    /**
     * Aop代理
     */
    private AopProxy aopProxy = new AopProxy();


    /**
     * 支持事件响应，会有一个监听
     */
    private BeanPostProcessor postProcessor;

    /**
     * 包装实例
     */
    private Object wrapperInstance;

    /**
     * 原始的通过反射new出来，要把包装起来，存下来
     */
    private Object originalInstance;

    public BeanWrapper(Object instance) {
        //从这里开始，我们要把动态的代码添加进来了
        this.wrapperInstance = aopProxy.getProxy(instance);
        this.originalInstance = instance;
    }

    public Object getWrappedInstance() {
        return this.wrapperInstance;
    }

    public Object getOriginalInstance() {
        return originalInstance;
    }

    public BeanPostProcessor getPostProcessor() {
        return postProcessor;
    }

    public void setPostProcessor(BeanPostProcessor postProcessor) {
        this.postProcessor = postProcessor;
    }

    public void setAopConfig(AopConfig config){
        aopProxy.setConfig(config);
    }
}
