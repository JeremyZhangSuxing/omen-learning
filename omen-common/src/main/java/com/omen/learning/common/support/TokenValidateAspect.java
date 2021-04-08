package com.omen.learning.common.support;

import com.omen.learning.common.enums.TokenState;
import com.omen.learning.common.utils.JwtUtils;
import com.weweibuy.framework.common.core.exception.Exceptions;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author zhang.suxing
 * @date 2020/11/10 22:31
 **/
@Slf4j
public class TokenValidateAspect implements MethodInterceptor {

    private final TokenInfoParser tokenParamInfoParse;
    private final String issuer;

    public TokenValidateAspect(TokenInfoParser tokenParamInfoParse, String issuer) {
        Assert.notNull(tokenParamInfoParse, "tokenParamInfoParse 不能为空");
        this.tokenParamInfoParse = tokenParamInfoParse;
        this.issuer = issuer;
    }

    /**
     * 通知
     */
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .orElseThrow(() -> Exceptions.system("99999", "token 异常"));
        HttpServletRequest httpServletRequest = attributes.getRequest();
        String token = httpServletRequest.getHeader("Authorization");
        //toke你解析处理
        TokenInfo tokenInfo = tokenParamInfoParse.parseIdempotentInfo(methodInvocation);
        log.info("===token信息===" + tokenInfo);
        //token验证处理
        tokenCondition(token, issuer, tokenInfo.getJwtId());
        Object proceed;
        proceed = methodInvocation.proceed();
        return proceed;
    }

    /**
     * tokeCondition
     */
    private void tokenCondition(String token, String issuer, String uniqueValue) {
        TokenState tokenState = JwtUtils.validateJWT(token, issuer, uniqueValue);
        switch (tokenState) {
            case EXPIRED:
                throw Exceptions.business("401", "token已经过期");
            case INVALID:
                throw Exceptions.business("405", "token无效，请检查");
            case VALID:
        }
    }

}
