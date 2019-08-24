package com.songjh.learn.spring.guides.lifeCycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created  by songjh on 2019-06-08 10:48.
 */
public class Student implements InitializingBean,DisposableBean {


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


    public Student() {
        System.out.println("\n Student construct");
    }


    public void init(){
        System.out.println("Student init");
    }


    public void destroyMethod(){
        System.out.println("Student destroyMethod");
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("Student postConstruct");
    }

    @PreDestroy
    public void PreDestroy(){
        System.out.println("Student PreDestroy");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("Student afterPropertiesSet");
    }


    @Override
    public void destroy() {
        System.out.println("Student destroy");

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
        System.out.println("Student set age");
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
        return "Student{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
