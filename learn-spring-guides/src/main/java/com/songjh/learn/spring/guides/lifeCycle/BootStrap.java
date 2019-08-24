package com.songjh.learn.spring.guides.lifeCycle;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created  by songjh on 2019-06-08 10:59.
 */
public class BootStrap {

    public static void main(String[] args){

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        Student student = (Student)context.getBean("student");

        System.out.println(student);

        Person person  = context.getBean("person", Person.class);

        System.out.println(person);


        context.destroy();

    }
}
