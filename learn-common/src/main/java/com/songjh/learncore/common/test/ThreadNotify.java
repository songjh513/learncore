package com.songjh.learncore.common.test;

import java.util.concurrent.Semaphore;

/**
 * Created  by songjh on 2019-03-17 08:56.
 */
public class ThreadNotify  {



    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            new Thread(new PrintDemo(100, 3, i)).start();
        }


    }

    static class PrintDemo implements Runnable {

        static Object LOCK = new Object();

        //当前数值
        private static  int count = 0;

        /**
         * 最大值
         */
        private int maxCount;

        /**
         * 线程数量
         */
        private int threadCount;

        /**
         * 当前线程编号
         */
        private int threadNo;

        public PrintDemo(int maxCount, int threadCount, int threadNo) {
            this.maxCount = maxCount;
            this.threadCount = threadCount;
            this.threadNo = threadNo;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (LOCK) {
                    if (count % threadCount != threadNo) {
                        if (count > maxCount) {
                            break;
                        }
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        if (count > maxCount) {
                            break;
                        }
                        System.out.println(threadNo + ":" + count);
                        count++;
                        LOCK.notifyAll();
                    }
                }
            }
        }
    }



}
