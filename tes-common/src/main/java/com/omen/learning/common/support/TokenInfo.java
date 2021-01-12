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
    private final String businessParam;
    private final JavaType javaType;
}
