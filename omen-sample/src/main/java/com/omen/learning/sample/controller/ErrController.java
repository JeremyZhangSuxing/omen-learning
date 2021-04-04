package com.omen.learning.sample.controller;

import com.omen.learning.common.enums.ResultEnum;
import com.omen.learning.common.utils.EnumUtil;
import com.omen.learning.common.utils.Result;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author : Knight
 * @date : 2021/4/4 10:12 上午
 */
@RestController
public class ErrController extends BasicErrorController {

    public ErrController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    @Override
    @RequestMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
        // 获取错误信息
        String message = body.get("message").toString();
        int code = EnumUtil.getCodeByMsg(message, ResultEnum.class);
        HttpStatus httpStatus;
        if (code == 500) {
            // 服务端异常，状态码为500
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            // 其余异常（手动throw）为逻辑校验，状态码为200
            httpStatus = HttpStatus.OK;
        }
        return new ResponseEntity(Result.failed(code, message), httpStatus);
    }
}
