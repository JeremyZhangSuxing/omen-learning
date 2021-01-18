package com.omen.learning.sample.controller;

import com.omen.learning.common.annotation.TokenValidate;
import com.omen.learning.common.enums.TokenState;
import com.omen.learning.common.support.JackJsonProUtils;
import com.omen.learning.sample.mybatis.po.CmOrder;
import com.omen.learning.common.utils.JwtUtils;
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

    /**
     * token生成
     */
    @GetMapping("/jwt")
    public CommonDataResponse<String> validaJwt(@RequestParam String iss, @RequestParam String uuid) {
        String token = JwtUtils.buildJWT(iss, 2, uuid);
        log.info("token :{}", token);
        return CommonDataResponse.success(token);
    }

    /**
     * token 过期验证
     */
    @GetMapping("jwtExpire")
    public CommonDataResponse<String> validateTokenExpire(@RequestParam String uuid) {
        String token = httpServletRequest.getHeader("Authorization");
        log.info("token : {}", token);
        TokenState jeremy = JwtUtils.validateJWT(token, "jeremy", uuid);
        return CommonDataResponse.success(jeremy.toString());
    }

    /**
     * 使用token做接口签名验证
     */
    @GetMapping("/token")
    @TokenValidate(jwtId = "#uuid")
    public CommonDataResponse<String> businessToken(@RequestParam String uuid) {
        List<CmOrder> cmOrders = testService.listOrders();
        return CommonDataResponse.success("token test success ：" + JackJsonProUtils.convertToJson(cmOrders));
    }

}
