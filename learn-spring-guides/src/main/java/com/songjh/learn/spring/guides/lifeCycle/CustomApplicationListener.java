package com.songjh.learn.spring.guides.lifeCycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;

/**
 * Created  by songjh on 2019-06-13 07:25.
 */
public class CustomApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("ContextRefreshedEvent trigger");
        ApplicationContext applicationContext = event.getApplicationContext();
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("event:"+beanDefinitionName);
        }
    }
}
