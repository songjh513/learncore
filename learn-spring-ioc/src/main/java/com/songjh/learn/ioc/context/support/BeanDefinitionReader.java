package com.songjh.learn.ioc.context.support;

import com.songjh.learn.ioc.beans.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 用对配置文件进行查找，读取、解析
 * Created  by songjh on 2019-04-09 07:42.
 */
public class BeanDefinitionReader {

    private Properties config = new Properties();

    private List<String> registyBeanClasses = new ArrayList<String>();

    //在配置文件中，用来获取自动扫描的包名的key
    private final String SCAN_PACKAGE = "scanPackage";

    public BeanDefinitionReader(String ... locations) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classpath:",""));


        try {
            config.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(null != is){is.close();}
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        doScanner(config.getProperty(SCAN_PACKAGE));
    }

    /**
     * 递归扫描所有的相关联的class，并且保存到一个List中
     * @param packageName
     */
    private void doScanner(String packageName) {

        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.","/"));

        File classDir = new File(url.getFile());

        for (File file : classDir.listFiles()){
            if(file.isDirectory()){
                doScanner(packageName + "." +file.getName());
            }else {
                registyBeanClasses.add(packageName + "." + file.getName().replace(".class",""));
            }
        }
    }

    public List<String> loadBeanDefinitions(){ return this.registyBeanClasses;}


    /**
     * 每注册一个className，就返回一个BeanDefinition
     * 只是为了对配置信息进行一个包装
     * @param className
     * @return
     */
    public BeanDefinition registerBean(String className){
        if(this.registyBeanClasses.contains(className)){
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setBeanClassName(className);
            beanDefinition.setFactoryBeanName(lowerFirstCase(className.substring(className.lastIndexOf(".") + 1)));
            return beanDefinition;
        }
        return null;
    }

    public Properties getConfig() {
        return config;
    }

    private String lowerFirstCase(String str){
        char [] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

}
