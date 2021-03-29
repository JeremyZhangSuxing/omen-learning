package com.omen.learning.sample.config;

import com.omen.learning.sample.servlet.LocalFilter;
import com.omen.learning.sample.support.LoginInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;

/**
 * @author suxing.zhang
 * @date 2021/3/28 14:42
 **/
@Configuration
public class OmenWebConfig implements WebMvcConfigurer {

    /**
     * 此处以缓存查询接口举例
     * test 所有地址都放行
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                //所有请求都被拦截包括静态资源
                .excludePathPatterns("/**");
                //放行的请求
//                .excludePathPatterns("/", "/login", "/css/**", "/fonts/**", "/images/**", "/js/**", "/cache/*");
    }


    /**
     * spring 中使用的是 /**
     * servlet 中是使用的是 /*
     */
    @Bean
    public FilterRegistrationBean<LocalFilter> localFilter() {
        FilterRegistrationBean<LocalFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LocalFilter());
        filterFilterRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
        filterFilterRegistrationBean.setName("localFilter");
        filterFilterRegistrationBean.setUrlPatterns(Collections.singleton("/cache/*"));
        return filterFilterRegistrationBean;
    }

    private void syncHeader(){}
}
