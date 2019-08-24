package com.songjh.learncore.common.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created  by songjh on 2019-06-18 21:22.
 */
public class ThreadExceptionDemo {
    private static int i;
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread( new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    System.out.println("demo"+i++);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }

            }
        });
        thread.start();

        TimeUnit.SECONDS.sleep(1);

        thread.interrupt();


    }
}
