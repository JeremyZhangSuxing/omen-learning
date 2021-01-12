package com.omen.learning.common.support;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.Assert;

import java.lang.reflect.Method;

/**
 * @author zhang.suxing
 * @date 2020/11/10 22:31
 **/
public class ServiceExceptionAspect implements MethodInterceptor {

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
        //在此处可进行自定义逻辑处理
        TokenInfo tokenInfo = tokenParamInfoParse.parseIdempotentInfo(methodInvocation);
        System.out.println(tokenInfo);
        Method method = methodInvocation.getMethod();
        System.err.println("开始对被标记的类进行切面" + method.getName());
        Object proceed;
        try {
            proceed = methodInvocation.proceed();
        } finally {
            System.out.println("error");
        }
        return proceed;
    }
}
