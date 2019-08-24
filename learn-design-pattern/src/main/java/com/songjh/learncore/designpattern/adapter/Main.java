package com.songjh.learncore.designpattern.adapter;

/**
 * Created  by songjh on 2019-06-13 21:05.
 */
public class Main {

    public static void main(String[] args) {
        LoginServiceAdapter loginServiceAdapter = new LoginServiceAdapter();
        loginServiceAdapter.loginByQQ("zhangsan","lishi");
    }
}
