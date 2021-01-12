package com.omen.learning.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举，定义token的三种状态
 *
 * @author Knight
 */
@Getter
@AllArgsConstructor
public enum TokenState {
    /**
     * 过期
     */
    EXPIRED("EXPIRED"),
    /**
     * 无效(token不合法)
     */
    INVALID("INVALID"),
    /**
     * 有效的
     */
    VALID("VALID");

    private final String state;
}
