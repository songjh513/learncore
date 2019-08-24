package com.songjh.learn.ioc.context;

import com.songjh.learn.ioc.annotation.Autowired;
import com.songjh.learn.ioc.annotation.Controller;
import com.songjh.learn.ioc.annotation.Service;
import com.songjh.learn.ioc.aop.AopConfig;
import com.songjh.learn.ioc.beans.BeanDefinition;
import com.songjh.learn.ioc.beans.BeanPostProcessor;
import com.songjh.learn.ioc.beans.BeanWrapper;
import com.songjh.learn.ioc.context.support.BeanDefinitionReader;
import com.songjh.learn.ioc.core.BeanFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created  by songjh on 2019-04-09 07:27.
 */
public class ApplicationContext extends DefaultListableBeanFactory implements BeanFactory {

    /**
     * 配置文件地址
     */
    private String[] configLocations;

    /**
     * BeanDefinitionReader
     */
    private BeanDefinitionReader reader;

    /**
     * 用来保证注册式单例的容器
     */
    private Map<String, Object> beanCacheMap = new HashMap<String, Object>();

    /**
     * 用来存储所有的被代理过的对象
     */
    private Map<String, BeanWrapper> beanWrapperMap = new ConcurrentHashMap<String, BeanWrapper>();


    public ApplicationContext(String ... configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    private void refresh() {
        //定位
        this.reader = new BeanDefinitionReader(configLocations);
        //加载
        List<String> beanDefinitions = reader.loadBeanDefinitions();
        //注册
        doRegisty(beanDefinitions);
        //依赖注入
        doAutowrited();
    }

    /**
     * 依赖注入
     */
    private void doAutowrited() {
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : this.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            if (!beanDefinitionEntry.getValue().isLazyInit()) {
                Object obj = getBean(beanName);
                System.out.println(obj.getClass());
            }
        }
        for (Map.Entry<String, BeanWrapper> beanWrapperEntry : this.beanWrapperMap.entrySet()) {
            populateBean(beanWrapperEntry.getKey(), beanWrapperEntry.getValue().getOriginalInstance());
        }
    }

    /**
     * 实现属性注入
     * @param beanName
     * @param instance
     */
    public void populateBean(String beanName, Object instance) {
        Class clazz = instance.getClass();
        if (!(clazz.isAnnotationPresent(Controller.class) ||
                clazz.isAnnotationPresent(Service.class))) {
            return;
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Autowired.class)) {
                continue;
            }
            Autowired autowired = field.getAnnotation(Autowired.class);
            String autowiredBeanName = autowired.value().trim();

            if ("".equals(autowiredBeanName)) {
                autowiredBeanName = field.getType().getName();
            }
            field.setAccessible(true);
            try {
                System.out.println("===" +instance +"," + autowiredBeanName + "," + this.beanWrapperMap.get(autowiredBeanName));
                field.set(instance, this.beanWrapperMap.get(autowiredBeanName).getWrappedInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 真正的将BeanDefinitions注册到beanDefinitionMap中
     * @param beanDefinitions
     */
    private void doRegisty(List<String> beanDefinitions) {
        //beanName有三种情况:
        //1、默认是类名首字母小写
        //2、自定义名字
        //3、接口注入
        try {
            for (String className : beanDefinitions) {
                Class<?> beanClass = Class.forName(className);
                //如果是一个接口，是不能实例化的,用它实现类来实例化
                if (beanClass.isInterface()) {
                    continue;
                }
                BeanDefinition beanDefinition = reader.registerBean(className);
                if (beanDefinition != null) {
                    this.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
                }
                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class<?> i : interfaces) {
                    //如果是多个实现类，只能覆盖
                    this.beanDefinitionMap.put(i.getName(), beanDefinition);
                }
                //到这里为止，容器初始化完毕
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //依赖注入，从这里开始，通过读取BeanDefinition中的信息
    //然后，通过反射机制创建一个实例并返回
    //Spring做法是，不会把最原始的对象放出去，会用一个BeanWrapper来进行一次包装
    //装饰器模式：
    //1、保留原来的OOP关系
    //2、我需要对它进行扩展，增强（为了以后AOP打基础）
    @Override
    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        try{
            //生成通知事件
            BeanPostProcessor beanPostProcessor = new BeanPostProcessor();
            Object instance = instantionBean(beanDefinition);
            if(null == instance){ return  null;}
            //在实例初始化以前调用一次
            beanPostProcessor.postProcessBeforeInitialization(instance,beanName);

            BeanWrapper beanWrapper = new BeanWrapper(instance);
            beanWrapper.setAopConfig(instantionAopConfig(beanDefinition));
            beanWrapper.setPostProcessor(beanPostProcessor);
            this.beanWrapperMap.put(beanName,beanWrapper);

            //在实例初始化以后调用一次
            beanPostProcessor.postProcessAfterInitialization(instance,beanName);
            //通过这样一调用，相当于给我们自己留有了可操作的空间
            return this.beanWrapperMap.get(beanName).getWrappedInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private AopConfig instantionAopConfig(BeanDefinition beanDefinition) throws  Exception{
        AopConfig config = new AopConfig();
        String expression = reader.getConfig().getProperty("pointCut");
        String[] before = reader.getConfig().getProperty("aspectBefore").split("\\s");
        String[] after = reader.getConfig().getProperty("aspectAfter").split("\\s");

        String className = beanDefinition.getBeanClassName();
        Class<?> clazz = Class.forName(className);

        Pattern pattern = Pattern.compile(expression);

        Class aspectClass = Class.forName(before[0]);
        //在这里得到的方法都是原生的方法
        for (Method m : clazz.getMethods()){
            Matcher matcher = pattern.matcher(m.toString());
            if(matcher.matches()){
                //能满足切面规则的类，添加的AOP配置中
                config.put(m,aspectClass.newInstance(),new Method[]{aspectClass.getMethod(before[1]),aspectClass.getMethod(after[1])});
            }
        }
        return  config;
    }


    /**
     * 传一个BeanDefinition，就返回一个实例Bean
     * @param beanDefinition
     * @return
     */
    private Object instantionBean(BeanDefinition beanDefinition){
        Object instance = null;
        String className = beanDefinition.getBeanClassName();
        try{
            //因为根据Class才能确定一个类是否有实例
            if(this.beanCacheMap.containsKey(className)){
                instance = this.beanCacheMap.get(className);
            }else{
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                this.beanCacheMap.put(className,instance);
            }
            return instance;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
