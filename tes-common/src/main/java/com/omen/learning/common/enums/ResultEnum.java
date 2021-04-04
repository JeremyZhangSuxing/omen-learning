package com.omen.learning.common.enums;

public enum ResultEnum implements CodeEnum {

    /**
     * clientid expired
     */
    ZERO_EXCEPTION(1052, "/ by zero")
    ;

    private int code;

    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

}