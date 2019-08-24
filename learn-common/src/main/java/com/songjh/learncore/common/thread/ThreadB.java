package com.songjh.learncore.common.thread;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created  by songjh on 2019-06-18 22:46.
 */
public class ThreadB extends Thread {

    private Object lockA;

    private Object lockB;

    private ArrayList<Integer> arrayList;

    public ThreadB(Object lockA,Object lockB,  ArrayList arrayList) {
        this.lockA = lockA;
        this.lockB = lockB;
        this.arrayList = arrayList;
    }


    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println("wait begin");
            try {
                for (Integer integer : arrayList) {
                    System.out.println("threadB-"+integer);
                    lockA.notify();
                    lockA.wait();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
