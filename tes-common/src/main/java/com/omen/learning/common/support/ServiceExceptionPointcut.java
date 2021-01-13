package com.omen.learning.common.support;

import com.omen.learning.common.annotation.ServiceException;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * @author zhang.suxing
 * @date 2020/11/10 22:20
 **/
public class ServiceExceptionPointcut extends StaticMethodMatcherPointcut {

    private final AnnotationMetaDataHolder annotationMetaDataHolder;

    public ServiceExceptionPointcut(AnnotationMetaDataHolder annotationMetaDataHolder) {
        this.annotationMetaDataHolder = annotationMetaDataHolder;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        ServiceException annotation = method.getAnnotation(ServiceException.class);
        boolean flag = annotation != null;
        if (flag) {
            System.err.println(method.getName() + method);
            annotationMetaDataHolder.putMetaData(method, annotation);

        }
        return flag;
    }
}
