package com.omen.learning.common.support;

import com.fasterxml.jackson.databind.JavaType;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : Knight
 * @date : 2021/1/12 5:44 下午
 */
@Data
@AllArgsConstructor
public class TokenInfo {
    /**
     * token唯一业务约束参数名称
     */
    private final String businessParam;
    /**
     * method返回类型
     */
    private final JavaType javaType;
    /**
     * token唯一约束参数值
     */
    private final String jwtId;
}
