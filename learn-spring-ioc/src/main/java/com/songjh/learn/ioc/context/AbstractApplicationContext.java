package com.songjh.learn.ioc.context;

/**
 * Created  by songjh on 2019-04-09 07:34.
 */
public abstract class AbstractApplicationContext {

    protected void onRefresh(){
        // For subclasses: do nothing by default.
    }

    protected abstract void refreshBeanFactory();
}
