package com.omen.learning.email.annotation;

import com.omen.learning.email.support.EmailHelper;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : Knight
 * @date : 2020/11/14 10:03 上午
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(EmailHelper.class)
public @interface EnableEmail {

}
