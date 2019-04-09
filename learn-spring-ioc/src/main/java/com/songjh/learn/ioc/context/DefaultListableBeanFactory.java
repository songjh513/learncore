package com.songjh.learn.ioc.context;

import com.songjh.learn.ioc.beans.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created  by songjh on 2019-04-09 07:39.
 */
public class DefaultListableBeanFactory extends AbstractApplicationContext {


    /**
     * beanDefinitionMap用来保存配置信息
     */
    protected Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String,BeanDefinition>();

    @Override
    protected void refreshBeanFactory() {

    }
}
