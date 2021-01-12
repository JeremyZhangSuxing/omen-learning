package com.omen.learning.common.support;

import com.fasterxml.jackson.databind.JavaType;
import com.omen.learning.common.annotation.ServiceException;
import lombok.Getter;

/**
 * @author : Knight
 * @date : 2021/1/12 4:49 下午
 */
@Getter
public class TokenAnnotationMetadata {
    /**
     * 业务参数
     */
    private final String businessParam;
    /**
     * 方法返回类型
     */
    private final JavaType returnType;

    public TokenAnnotationMetadata(ServiceException serviceException, JavaType javaType) {
        businessParam = serviceException.jwtId();
        returnType = javaType;
    }
}
