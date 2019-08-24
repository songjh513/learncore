package com.songjh.learncore.common.utils.concurrent;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 并发执行工具类
 * Created  by songjh on 2019-03-14 23:24.
 */
public class ConcurrentUtils {


    /**
     * 并发执行
     *
     * @param handlers     处理器列表
     * @param taskExecutor 线程池
     * @param timeout      超时时间，单位毫秒
     */
    public static void execute(List<BasicThreadHandler> handlers, ThreadPoolTaskExecutor taskExecutor, long timeout) {

        //创建一个计时器
        StopWatch watch = new StopWatch();
        watch.start();

        try {
            //等待所有线程执行完毕
            HandlerCountDownLatch handlerCountDownLatch = new HandlerCountDownLatch(handlers.size());

            //上锁完执行普通线程任务
            executeHandler(handlers, taskExecutor, handlerCountDownLatch);

            //监控线程和队列数量
            digestTaskExecutor("ConcurrentUtils afterSubmitTask", taskExecutor, watch.getTime());

            //主线程阻塞，等待线程任务执行完毕
            handlerCountDownLatch.await(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            watch.stop();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            watch.stop();
            digestTaskExecutor("ConcurrentUtils finish", taskExecutor, watch.getTime());
        }

    }

    /**
     * 上锁后执行普通线程任务
     *
     * @param handlers
     * @param taskExecutor
     * @param countDownLatch
     */
    private static void executeHandler(List<BasicThreadHandler> handlers, ThreadPoolTaskExecutor taskExecutor, HandlerCountDownLatch countDownLatch) {
        for (BasicThreadHandler handler : handlers) {
            taskExecutor.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() {
                    //计时并执行
                    doInvoke(handler, countDownLatch);
                    //通知主线程执行完毕
                    countDownLatch.countDown();
                    return true;
                }
            });
        }
    }

    private static void doInvoke(BasicThreadHandler handler, HandlerCountDownLatch countDownLatch) {
        String handlerName = getHandlerName(handler);
        StopWatch watch = new StopWatch();
        watch.start();
        try {
            Object result = handler.doExecute();
            handler.setResult(result);
            //日志
            watch.stop();
            if (countDownLatch.isFinished()) {
                System.out.println("执行handler超时，handlerName=" + handlerName);
            } else {
                System.out.println(String.format("执行handler成功，handlerName=%s time=%sms", handlerName, String.valueOf(watch.getTime())));

            }
        } catch (Exception e) {
            watch.stop();
            System.out.println("执行handler失败，handlerName=" + handlerName);
        }
    }

    /**
     * 获取处理器名称
     *
     * @param handler
     * @return
     */
    private static String getHandlerName(BasicThreadHandler handler) {
        String simpleName = handler.getClass().getSimpleName();
        String fullClassName = handler.getClass().getName();
        //匿名内部类simpleName是空
        if (StringUtils.isBlank(simpleName) || fullClassName.contains("$")) {
            int lastIndex = fullClassName.lastIndexOf(".") + 1;
            simpleName = fullClassName.substring(lastIndex, fullClassName.length());
            if (StringUtils.isNotBlank(handler.getHandlerName())) {
                simpleName = simpleName + "|" + handler.getHandlerName();
            }
        }
        return simpleName;
    }

    private static void digestTaskExecutor(String scene, ThreadPoolTaskExecutor taskExecutor, long time) {
        int activeCount = taskExecutor.getActiveCount();
        int queueSize = taskExecutor.getThreadPoolExecutor().getQueue().size();

        System.out.println(String.format("scene=%s，time=%d ms activeCount=%d queueSize=%d", scene, time, activeCount, queueSize));

    }
}
