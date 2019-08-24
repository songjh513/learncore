package com.songjh.learncore.common.thread;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created  by songjh on 2019-06-18 22:49.
 */
public class ThreadWaitNotifyMain {

    public static void main(String[] args){
        Object lockA = new Object();
        Object lockB = new Object();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            list.add(i);
        }
        System.out.println(list);

        new ThreadA(lockA,lockB,list).start();
        new ThreadB(lockA,lockB,list).start();
    }
}
