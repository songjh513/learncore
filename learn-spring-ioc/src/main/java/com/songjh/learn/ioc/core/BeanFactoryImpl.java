package com.songjh.learn.ioc.core;

import com.songjh.learn.ioc.bean.MyBeanDefinition;
import com.songjh.learn.ioc.bean.ConstructorArg;
import com.songjh.learn.ioc.utils.BeanUtils;
import com.songjh.learn.ioc.utils.ClassUtils;
import com.songjh.learn.ioc.utils.ReflectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created  by songjh on 2019-04-07 14:13.
 */
public class BeanFactoryImpl implements BeanFactory {


    private static final ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<String, MyBeanDefinition> beanDefineMap = new ConcurrentHashMap<>();

    private static final Set<String> beanNameSet = Collections.synchronizedSet(new HashSet<>());


    @Override
    public Object getBean(String name) throws Exception {
        //查找对象是否已经实例化过
        Object bean = beanMap.get(name);
        if (bean != null) {
            return bean;
        }
        //如果没有实例化，那就需要调用createBean来创建对象
        bean = createBean(beanDefineMap.get(name));

        if (bean != null) {

            //对象创建成功以后，注入对象需要的参数
            populatebean(bean);

            //再吧对象存入Map中方便下次使用。
            beanMap.put(name, bean);
        }

        //结束返回
        return bean;
    }

    protected void registerBean(String name, MyBeanDefinition bd) {
        beanDefineMap.put(name, bd);
        beanNameSet.add(name);
    }

    private Object createBean(MyBeanDefinition beanDefinition) throws Exception {
        String beanName = beanDefinition.getClassName();
        Class clz = ClassUtils.loadClass(beanName);
        if (clz == null) {
            throw new Exception("can not find bean by beanName");
        }
        List<ConstructorArg> constructorArgs = beanDefinition.getConstructorArgs();
        if (constructorArgs != null && !constructorArgs.isEmpty()) {
            List<Object> objects = new ArrayList<>();
            for (ConstructorArg constructorArg : constructorArgs) {
                if (constructorArg.getValue() != null) {
                    objects.add(constructorArg.getValue());
                } else {
                    objects.add(getBean(constructorArg.getRef()));
                }
            }
            Class[] constructorArgTypes = objects.stream().map(it -> it.getClass()).collect(Collectors.toList()).toArray(new Class[]{});
            Constructor constructor = clz.getConstructor(constructorArgTypes);
            return BeanUtils.instanceByCglib(clz, constructor, objects.toArray());
        } else {
            return BeanUtils.instanceByCglib(clz, null, null);
        }
    }

    private void populatebean(Object bean) throws Exception {
        Field[] fields = bean.getClass().getSuperclass().getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                String beanName = field.getName();
                beanName = StringUtils.uncapitalize(beanName);
                if (beanNameSet.contains(field.getName())) {
                    Object fieldBean = getBean(beanName);
                    if (fieldBean != null) {
                        ReflectionUtils.injectField(field, bean, fieldBean);
                    }
                }
            }
        }
    }


}
