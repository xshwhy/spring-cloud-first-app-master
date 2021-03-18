package com.fayfox.annotation;

import java.lang.annotation.*;

/**
 * 系统日志，主要是为了试验一下自定义注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogger {
    String value() default "";
}
