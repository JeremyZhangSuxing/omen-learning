package com.omen.learning.common.annotation;

import com.omen.learning.common.support.TokenValidateConfig;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhang.suxing
 * @date 2020/11/10 21:51
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({TokenValidateConfig.class})
public @interface EnableTokenValidate {
    /**
     * 切面执行顺序
     * 注解式切面
     */
    int order() default Ordered.LOWEST_PRECEDENCE;

    AdviceMode mode() default AdviceMode.PROXY;

    boolean proxyTargetClass() default false;

}
