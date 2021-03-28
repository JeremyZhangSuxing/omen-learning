package com.omen.learning.sample.config;

import com.omen.learning.sample.support.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author suxing.zhang
 * @date 2021/3/28 14:42
 **/
@Configuration
public class OmenWebConfig implements WebMvcConfigurer {

    /**
     * 此处以缓存查询接口举例
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                //所有请求都被拦截包括静态资源
                .addPathPatterns("/**")
                //放行的请求
                .excludePathPatterns("/", "/login", "/css/**", "/fonts/**", "/images/**", "/js/**", "/cache/*","www.baidu.com");
    }

}
