package com.songjh.learncore.webmvc.annotation;

import java.lang.annotation.*;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAutowried {
    String value() default  "";
}
