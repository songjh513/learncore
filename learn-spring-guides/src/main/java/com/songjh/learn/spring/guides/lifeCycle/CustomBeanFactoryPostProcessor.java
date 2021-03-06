package com.songjh.learn.spring.guides.lifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Iterator;

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    /**
     * 主要是用来自定义修改持有的bean
     * ConfigurableListableBeanFactory 其实就是DefaultListableBeanDefinition对象
     * @param beanFactory
     * @throws BeansException
     */

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("调用了自定义的BeanFactoryPostProcessor " );
        Iterator it = beanFactory.getBeanNamesIterator();

        String[] names = beanFactory.getBeanDefinitionNames();
        // 获取了所有的bean名称列表
        for(int i=0; i<names.length; i++){
            String name = names[i];

            BeanDefinition bd = beanFactory.getBeanDefinition(name);
            if(bd.getBeanClassName().equals("com.songjh.learn.spring.guides.lifeCycle.Student")){
                System.out.println("setAttribute");
                bd.getPropertyValues().addPropertyValue("age","18");
                bd.getPropertyValues().addPropertyValue("name","貂蝉");
                bd.getPropertyValues().addPropertyValue("sex","女");
            }
            System.out.println(name + " bean properties: " + bd.getPropertyValues().toString());
            // 本内容只是个demo，打印持有的bean的属性情况
        }
    }
}