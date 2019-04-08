package com.songjh.learn.ioc.bean;

/**
 * Created  by songjh on 2019-04-07 14:07.
 */
public class ConstructorArg {

    private int index;
    private String ref;
    private String name;
    private Object value;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
