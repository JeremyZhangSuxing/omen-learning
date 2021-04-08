package com.omen.learning.common.enums;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomException extends RuntimeException {

    private int code;

    private String msg;

    public CustomException(ResultEnum resultEnum) {
        // 自定义错误栈中显示的message
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    public CustomException(int code, String msg) {
        // 自定义错误栈中显示的message
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}