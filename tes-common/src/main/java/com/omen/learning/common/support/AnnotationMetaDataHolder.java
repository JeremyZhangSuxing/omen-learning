package com.omen.learning.common.support;

import com.fasterxml.jackson.databind.JavaType;
import com.omen.learning.common.annotation.ServiceException;
import com.weweibuy.framework.common.core.utils.JackJsonUtils;
import org.springframework.core.GenericTypeResolver;
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

    public synchronized void putMetaData(Method method, ServiceException serviceException) {
        MethodParameter methodParameter = new MethodParameter(method, -1);
        Type parameterType = methodParameter.getNestedGenericParameterType();
        JavaType javaType = JackJsonUtils.getCamelCaseMapper().getTypeFactory().constructType(GenericTypeResolver.resolveType(parameterType, method.getReturnType()));
        hashMap.put(method, new TokenAnnotationMetadata(serviceException, javaType));
    }

    public TokenAnnotationMetadata getMetaData(Method method) {
        return hashMap.get(method);
    }
}
