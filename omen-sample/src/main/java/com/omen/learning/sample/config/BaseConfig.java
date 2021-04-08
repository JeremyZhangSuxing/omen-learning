package com.omen.learning.sample.config;

import com.omen.learning.common.entity.BillDTO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

/**
 * @author : Knight
 * @date : 2020/11/12 1:46 下午
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties
public class BaseConfig {
    //1、WebMvcConfigurer定制化SpringMVC的功能
//    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper urlPathHelper = new UrlPathHelper();
                // 不移除；后面的内容。矩阵变量功能就可以生效
                urlPathHelper.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(urlPathHelper);
            }
            //对于定制化需求可这样操作，一般意义不大
            @Override
            public void addFormatters(FormatterRegistry registry) {
                registry.addConverter((Converter<String, BillDTO>) source -> {
                    if (!StringUtils.isEmpty(source)) {
                        BillDTO pet = new BillDTO();
                        String[] split = source.split(",");
                        pet.setOrderNo(split[0]);
                        pet.setId(Long.parseLong(split[1]));
                        return pet;
                    }
                    return null;
                });
            }
        };
    }
}
