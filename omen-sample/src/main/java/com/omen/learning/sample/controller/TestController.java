package com.omen.learning.sample.controller;

import com.omen.learning.common.enums.TokenState;
import com.omen.learning.sample.mybatis.po.CmOrder;
import com.omen.learning.sample.support.JwtUtils;
import com.omen.learning.sample.test.TestService;
import com.weweibuy.framework.common.core.model.dto.CommonDataResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : Knight
 * @date : 2021/1/8 5:26 下午
 */
@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;
    private final HttpServletRequest httpServletRequest;

    @GetMapping("/orders")
    public CommonDataResponse<List<CmOrder>> queryList() {
        return CommonDataResponse.success(testService.listOrders());
    }

    @GetMapping("/jwt")
    public CommonDataResponse<String> validaJwt(@RequestParam String iss, @RequestParam String uuid) {
        String token = JwtUtils.buildJWT(iss, 1, uuid);
        log.info("token :{}", token);
        TokenState tokenState = JwtUtils.validateJWT(token, "jeremy", uuid);
        return CommonDataResponse.success(tokenState.name());
    }

    @GetMapping("jwtExpire")
    public CommonDataResponse<String> validateTokenExpire(@RequestParam String uuid) {
        String token = httpServletRequest.getHeader("Authorization");
        log.info("token : {}", token);
        TokenState jeremy = JwtUtils.validateJWT(token, "jeremy", uuid);
        return CommonDataResponse.success(jeremy.toString());
    }
}
