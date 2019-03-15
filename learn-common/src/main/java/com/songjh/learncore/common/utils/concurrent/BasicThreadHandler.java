package com.songjh.learncore.common.utils.concurrent;

/**
 * Created  by songjh on 2019-03-14 23:30.
 */
public abstract class BasicThreadHandler<T> {

    /**
     * 处理器名称
     */
    private String handlerName;

    /**
     * 执行结果
     */
    private T result;

    abstract T doExecute();

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }
}
