package com.omen.learning.common.annotation;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * @author zhang.suxing
 * @date 2020/11/10 22:20
 **/
public class ServiceExceptionPointcut extends StaticMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        ServiceException annotation = method.getAnnotation(ServiceException.class);
        boolean flag = annotation != null;
        if (flag) {
            System.out.println(method.getName()+method);
        }
        return flag;
    }
}
