package com.omen.learning.bean.test.annotattion;

import com.omen.learning.bean.test.support.SpecificBeanRegistry;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : Knight
 * @date : 2020/11/14 4:33 下午
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(SpecificBeanRegistry.class)
public @interface EnableBeanRegistry {
    String[] value() default {};

    String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};
}
