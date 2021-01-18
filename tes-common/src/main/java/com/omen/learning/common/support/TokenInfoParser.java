package com.omen.learning.common.support;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @author : Knight
 * @date : 2021/1/12 5:43 下午
 */
public class TokenInfoParser extends CommonExpressionEvaluator {

    private final AnnotationMetaDataHolder annotationMetaDataHolder;

    public TokenInfoParser(AnnotationMetaDataHolder annotationMetaDataHolder) {
        Assert.notNull(annotationMetaDataHolder, "annotationMetaDataHolder 不能为空");
        this.annotationMetaDataHolder = annotationMetaDataHolder;

    }

    /**
     * 解析数据
     */
    public TokenInfo parseIdempotentInfo(MethodInvocation methodInvocation) {
        Method method = methodInvocation.getMethod();
        Object[] arguments = methodInvocation.getArguments();
        Object target = methodInvocation.getThis();
        //spel 解析参数
        TokenAnnotationMetadata metaData = annotationMetaDataHolder.getMetaData(method);
        String jwtIdParam = Optional.ofNullable(metaData.getJwtId()).orElse(null);
        Assert.notNull(jwtIdParam, "解析token的必要参数为null");
        String jwtId = evaluatorExpressionStr(metaData.getJwtId(), target, target.getClass(), method, arguments);

        //spel 表达式 获取业务参数值
        return new TokenInfo(metaData.getJwtId(), metaData.getReturnType(), jwtId);
    }


    @Override
    protected Object getRootObject(Object target, Class<?> clazz, Method method, Object[] args) {
        return null;
    }
}
