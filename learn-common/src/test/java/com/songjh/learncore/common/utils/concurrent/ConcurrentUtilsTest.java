package com.songjh.learncore.common.utils.concurrent;


import com.google.common.collect.Lists;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created  by songjh on 2019-03-14 23:27.
 */
class ConcurrentUtilsTest {

    static ExecutorService fixThreadPool = Executors.newFixedThreadPool(10);


    public static void main(String[] args) {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(30);
        taskExecutor.setMaxPoolSize(100);
        taskExecutor.setQueueCapacity(1000);
        taskExecutor.initialize();

        for (int i = 0; i < 1; i++) {
            fixThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    List<BasicThreadHandler> handlers = getHandlers();

                    ConcurrentUtils.execute(handlers, taskExecutor, 5000);
                }
            });
        }
    }

    private static List<BasicThreadHandler> getHandlers() {

        BasicThreadHandler<String> handler1 = new BasicThreadHandler<String>() {
            @Override
            String doExecute() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("handler1 is running in" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "handler1";
            }
        };

        BasicThreadHandler<String> handler2 = new BasicThreadHandler<String>() {
            @Override
            String doExecute() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("handler2 is running in" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "handler2";
            }
        };
        List<BasicThreadHandler> handlers = Lists.newArrayList();
        handlers.add(handler1);
        handlers.add(handler2);

        return handlers;
    }

}