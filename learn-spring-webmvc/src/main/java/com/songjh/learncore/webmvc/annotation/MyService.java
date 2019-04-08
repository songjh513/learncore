package com.songjh.learncore.webmvc.annotation;

import java.lang.annotation.*;

/**
 * Created  by songjh on 2019-04-08 21:45.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyService {
    String value() default  "";
}