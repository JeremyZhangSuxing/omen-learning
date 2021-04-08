package com.omen.learning.common.support;

import com.fasterxml.jackson.databind.JavaType;
import com.omen.learning.common.annotation.TokenValidate;
import org.springframework.core.MethodParameter;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Knight
 * @date : 2021/1/12 5:33 下午
 */
public class AnnotationMetaDataHolder {
    private final Map<Method, TokenAnnotationMetadata> hashMap = new HashMap<>(16);


    public synchronized void putMetaData(Method method, TokenValidate tokenValidate) {
        MethodParameter methodParameter = new MethodParameter(method, -1);
        Type parameterType = methodParameter.getNestedGenericParameterType();
        JavaType javaType = JackJsonProUtils.getJavaType(parameterType, method);
        hashMap.put(method, new TokenAnnotationMetadata(tokenValidate, javaType));
    }

    public TokenAnnotationMetadata getMetaData(Method method) {
        return hashMap.get(method);
    }
}
