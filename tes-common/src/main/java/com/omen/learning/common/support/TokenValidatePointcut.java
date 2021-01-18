package com.omen.learning.common.support;

import com.omen.learning.common.annotation.TokenValidate;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * @author zhang.suxing
 * @date 2020/11/10 22:20
 **/
public class TokenValidatePointcut extends StaticMethodMatcherPointcut {

    private final AnnotationMetaDataHolder annotationMetaDataHolder;

    public TokenValidatePointcut(AnnotationMetaDataHolder annotationMetaDataHolder) {
        this.annotationMetaDataHolder = annotationMetaDataHolder;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        TokenValidate annotation = method.getAnnotation(TokenValidate.class);
        boolean flag = annotation != null;
        if (flag) {
            System.err.println(method.getName() + method);
            annotationMetaDataHolder.putMetaData(method, annotation);
        }
        return flag;
    }
}
