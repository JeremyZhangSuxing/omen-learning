package com.omen.learning.common.support;

import com.weweibuy.framework.common.core.expression.CommonCachedExpressionEvaluator;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.Assert;

import java.lang.reflect.Method;

/**
 * @author : Knight
 * @date : 2021/1/12 5:43 下午
 */
public class TokenInfoParser extends CommonCachedExpressionEvaluator {

    private AnnotationMetaDataHolder annotationMetaDataHolder;


    public TokenInfoParser(AnnotationMetaDataHolder annotationMetaDataHolder) {
        Assert.notNull(annotationMetaDataHolder, "annotationMetaDataHolder 不能为空");
        this.annotationMetaDataHolder = annotationMetaDataHolder;

    }

    /**
     * 解析数据
     */
    public TokenInfo parseIdempotentInfo(MethodInvocation methodInvocation) {
        Method method = methodInvocation.getMethod();
        TokenAnnotationMetadata metaData = annotationMetaDataHolder.getMetaData(method);
        return new TokenInfo(metaData.getBusinessParam(), metaData.getReturnType());
    }
}
