package com.omen.learning.common.annotation;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @author zhang.suxing
 * @date 2020/11/10 22:31
 **/
public class ServiceExceptionAspect implements MethodInterceptor {

    /**
     * 通知
     */
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Method method = methodInvocation.getMethod();
        System.out.println(method.getName());
        Object proceed;
        try {
            proceed = methodInvocation.proceed();
        } finally {
            System.out.println("111");
        }
        return proceed;
    }
}
