package com.omen.swagger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author : Knight
 * @date : 2021/8/27 3:05 下午
 */
@Configuration
@Import(SwaggerConfig.class)
public class SwaggerAutoConfiguration {
}
