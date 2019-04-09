package com.songjh.learn.ioc.annotation;

import java.lang.annotation.*;


/**
 * 自动注入
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
	String value() default "";
}
