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
    private final String jwtId;
    /**
     * 方法返回类型
     */
    private final JavaType returnType;

    /**
     * 构造函数传入直接上的值
     */
    public TokenAnnotationMetadata(ServiceException serviceException, JavaType javaType) {
        jwtId = serviceException.jwtId();
        returnType = javaType;
    }
}
