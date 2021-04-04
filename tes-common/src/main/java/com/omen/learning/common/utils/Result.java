package com.omen.learning.common.utils;

import com.omen.learning.common.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int SUCCESS_CODE = 0;

    public static final int FAIL_CODE = 1;

    public static final String SUCCESS_MSG = "success";

    public static final String FAIL_MSG = "error";

    /**
     * 返回标记：成功标记=0，失败标记=1
     */
    private int code;

    /**
     * 返回信息
     */
//    @JsonProperty("message")
    private String message;

    /**
     * 数据
     */
//    @JsonProperty("results")
    private T results;

    public static <T> Result<T> ok() {
        return restResult(null, SUCCESS_CODE, SUCCESS_MSG);
    }

    public static <T> Result<T> ok(T data) {
        return restResult(data, SUCCESS_CODE, SUCCESS_MSG);
    }

    public static <T> Result<T> ok(T data, String msg) {
        return restResult(data, SUCCESS_CODE, msg);
    }

    public static <T> Result<T> failed() {
        return restResult(null, FAIL_CODE, FAIL_MSG);
    }

    public static <T> Result<T> failed(String msg) {
        return restResult(null, FAIL_CODE, msg);
    }

    public static <T> Result<T> failed(int code, String msg) {
        return restResult(null, code, msg);
    }

    public static <T> Result<T> failed(ResultEnum resultEnum) {
        return restResult(null, resultEnum.getCode(), resultEnum.getMsg());
    }

    private static <T> Result<T> restResult(T data, int code, String msg) {
        Result<T> apiResult = new Result<>();
        apiResult.setCode(code);
        apiResult.setResults(data);
        apiResult.setMessage(msg);
        return apiResult;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println("说谢谢可就太客气啦！！！！");
            try {
                Thread.sleep(1000);
                System.err.println("但不说谢谢， 好像又不太礼貌诶！！！");
            } catch (InterruptedException e) {

            }
        }

    }
}