package com.omen.learning.common.support;

import com.omen.learning.common.enums.TokenState;
import com.omen.learning.common.utils.JwtUtils;
import com.weweibuy.framework.common.core.exception.Exceptions;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author zhang.suxing
 * @date 2020/11/10 22:31
 **/
public class ServiceExceptionAspect implements MethodInterceptor {
    @Value("${jwt.token.key:jeremy}")
    private String issuer;
    private final TokenInfoParser tokenParamInfoParse;

    public ServiceExceptionAspect(TokenInfoParser tokenParamInfoParse) {
        Assert.notNull(tokenParamInfoParse, "tokenParamInfoParse 不能为空");
        this.tokenParamInfoParse = tokenParamInfoParse;
    }

    /**
     * 通知
     */
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .orElseThrow(() -> Exceptions.system("401", "token 异常"));
        HttpServletRequest httpServletRequest = attributes.getRequest();
        String token = httpServletRequest.getHeader("Authorization");
        //在此处可进行自定义逻辑处理
        TokenInfo tokenInfo = tokenParamInfoParse.parseIdempotentInfo(methodInvocation);
        System.err.println("===token信息===" + tokenInfo);
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
                throw Exceptions.business("401", "超时");
            case INVALID:
                throw Exceptions.business("405", "无效");
            case VALID:
        }
    }

}
