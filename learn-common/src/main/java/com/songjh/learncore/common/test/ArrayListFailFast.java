package com.songjh.learncore.common.test;


import java.util.ArrayList;

/**
 * Created  by songjh on 2019-03-17 21:41.
 */
public class ArrayListFailFast {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            ArrayList<String> list = new ArrayList<>();
            list.add("one");
            list.add("two");
            list.add("three");

            for (String s : list) {
                if ("two".equals(s)) {
                    list.remove(s);
                }
            }

            System.out.println(list);
        }
    }
}
