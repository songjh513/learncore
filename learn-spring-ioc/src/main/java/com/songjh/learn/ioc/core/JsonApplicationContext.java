package com.songjh.learn.ioc.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.songjh.learn.ioc.bean.BeanDefinition;
import com.songjh.learn.ioc.utils.JsonUtils;

import java.io.InputStream;
import java.util.List;

/**
 * Created  by songjh on 2019-04-07 14:12.
 */
public class JsonApplicationContext extends BeanFactoryImpl{

    private String fileName;

    public JsonApplicationContext(String fileName) {
        this.fileName = fileName;
        init();
    }

    public void init(){
        loadFile();
    }

    private void loadFile(){

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

        List<BeanDefinition> beanDefinitions = JsonUtils.readValue(is,new TypeReference<List<BeanDefinition>>(){});

        if(beanDefinitions != null && !beanDefinitions.isEmpty()) {

            for (BeanDefinition beanDefinition : beanDefinitions) {
                registerBean(beanDefinition.getName(), beanDefinition);
            }
        }

    }

}
