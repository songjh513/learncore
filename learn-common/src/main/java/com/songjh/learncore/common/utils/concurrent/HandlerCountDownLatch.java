package com.songjh.learncore.common.utils.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 可用统计handler是否执行完成
 * Created  by songjh on 2019-03-14 23:43.
 */
public class HandlerCountDownLatch extends CountDownLatch {


    private boolean finished;

    /**
     * Constructs a {@code CountDownLatch} initialized with the given count.
     *
     * @param count the number of times {@link #countDown} must be invoked
     *              before threads can pass through {@link #await}
     * @throws IllegalArgumentException if {@code count} is negative
     */
    public HandlerCountDownLatch(int count) {
        super(count);
        finished = false;
    }

    @Override
    public boolean await(long timeout, TimeUnit unit) throws InterruptedException {
        Boolean result = super.await(timeout, unit);
        finished = true;
        return result;
    }

    public boolean isFinished() {
        return finished;
    }
}
