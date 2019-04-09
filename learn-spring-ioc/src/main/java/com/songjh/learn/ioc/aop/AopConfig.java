package com.songjh.learn.ioc.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


//只是对application中的expression的封装
//目标代理对象的一个方法要增强
//由用自己实现的业务逻辑去增强
//配置文件的目的：告诉Spring，哪些类的哪些方法需要增强，增强的内容是什么
//对配置文件中所体现的内容进行封装
public class AopConfig {

    //以目标对象需要增强的Method作为key，需要增强的代码内容作为value
    private Map<Method,Aspect> points = new HashMap<Method, Aspect>();

    public void put(Method target,Object aspect,Method[] points){
        this.points.put(target,new Aspect(aspect,points));
    }

    public Aspect get(Method method){
        return this.points.get(method);
    }

    public boolean contains(Method method){
        return this.points.containsKey(method);
    }



    //对增强的代码的封装
    public class Aspect {
        private Object aspect; //待会将LogAspet这个对象赋值给它
        private Method[] points;//会将LogAspet的before方法和after方法赋值进来

        public Aspect(Object aspect, Method[] points){
            this.aspect = aspect;
            this.points = points;
        }

        public Object getAspect() {
            return aspect;
        }

        public Method[] getPoints() {
            return points;
        }
    }

}
