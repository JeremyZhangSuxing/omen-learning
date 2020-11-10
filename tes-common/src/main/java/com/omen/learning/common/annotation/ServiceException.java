package com.omen.learning.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhang.suxing
 * @date 2020/11/10 21:48
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ServiceException {
    int code() default 0;

    String message() default "";
}
