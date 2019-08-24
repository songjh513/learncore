package com.songjh.learn.spring.guides.lifeCycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created  by songjh on 2019-06-08 11:05.
 */
public class Person implements InitializingBean,DisposableBean{

    /**
     * name
     */
    private String name;

    /**
     * age
     */
    private String age;

    /**
     * sex
     */
    private String sex;


    public Person() {
        System.out.println("\n Person construct");
    }


    public void init(){
        System.out.println("Person init");
    }


    public void destroyMethod(){
        System.out.println("Person destroyMethod");
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("Person postConstruct");
    }

    @PreDestroy
    public void PreDestroy(){
        System.out.println("Person PreDestroy");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("Person afterPropertiesSet");
    }


    @Override
    public void destroy() {
        System.out.println("Person destroy");

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        System.out.println("Person set age");
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
