package com.songjh.learn.ioc.core;


import com.songjh.learn.ioc.entity.Robot;

/**
 * Created  by songjh on 2019-04-07 15:22.
 */
public class JsonApplicationContextTest {

    public static void main(String[] args) throws Exception {
        JsonApplicationContext applicationContext = new JsonApplicationContext("json/application.json");
        Robot aiRobot = (Robot) applicationContext.getBean("robot");
        aiRobot.show();
    }
}