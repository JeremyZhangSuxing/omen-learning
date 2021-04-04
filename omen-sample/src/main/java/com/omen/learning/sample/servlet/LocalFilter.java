package com.omen.learning.sample.servlet;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.omen.learning.common.enums.CustomException;
import com.omen.learning.common.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author : Knight
 * @date : 2021/3/29 11:22 上午
 */
@Slf4j
public class LocalFilter implements Filter {
    static final TransmittableThreadLocal<Map<String, String>> headerRepository = new TransmittableThreadLocal<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("LocalFilter --  init here -----");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {
            log.info("---- LocalFilter doFilter start here----");
            doSyncHeader((HttpServletRequest) request, (HttpServletResponse) response);
            //测试代码  filter 的异常 会被ErrController 处理 但很少这样字用
//            int a = 1 / 0;
            chain.doFilter(request, response);
            log.info("---- LocalFilter doFilter end here-l---");
        } catch (Exception e) {
            throw new CustomException(ResultEnum.ZERO_EXCEPTION);
        }
    }

    /**
     * 接口请求 时间设置head参数
     */
    private void doSyncHeader(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("Authorization");
        MDC.put("token", token);
        //设置head参数到线程
        headerRepository.set(MDC.getCopyOfContextMap());
    }
}
