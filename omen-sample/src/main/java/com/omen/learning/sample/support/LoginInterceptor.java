package com.omen.learning.sample.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author suxing.zhang
 * @date 2021/3/28 14:50
 **/
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {


    private static final String knight = "knight";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("preHandle --->>> {}", requestURI);
        //可以在此处对request 做预处理 比如是否登录。。。
        String token = request.getHeader("token");
        if (token.equals(knight)) {
            log.info("token  is wrong");
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        log.info("postHandle --->>> 请求处理完后到mvc  {} ", request.getRequestURI());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        log.info("afterCompletion --->>> 请求处理结束之后的清理工作 {}", request.getRequestURI());
    }
}
