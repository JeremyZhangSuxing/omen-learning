package com.omen.learning.idempotence.annotation;

/**
 * @author : Knight
 * @date : 2020/11/14 4:26 下午
 */
public @interface Idempotence {
    String value() default "";
    String name() default "";
}
