package com.omen.learning.sample.controller;

import com.omen.learning.common.annotation.TokenValidate;
import com.omen.learning.common.enums.TokenState;
import com.omen.learning.common.utils.JwtUtils;
import com.weweibuy.framework.common.core.model.dto.CommonDataResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author : Knight
 * @date : 2021/1/8 5:26 下午
 */
@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final HttpServletRequest httpServletRequest;
    private final RedissonClient redissonClient;

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
        return CommonDataResponse.success("token test success");
    }

    @GetMapping
    public void redisson(@RequestParam String key) throws InterruptedException {
        RLock lock = redissonClient.getLock(key);
        lock.lock();
        TimeUnit.SECONDS.sleep(100);
        lock.unlock();
    }


}
