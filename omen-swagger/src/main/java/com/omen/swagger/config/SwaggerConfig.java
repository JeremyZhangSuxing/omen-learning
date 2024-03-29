package com.omen.swagger.config;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

/**
 * @author : Knight
 * @date : 2021/8/27 3:05 下午
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({SwaggerProperties.class})
//根据配置文件决定是否自动配置
@ConditionalOnProperty(prefix = "javadaily.swagger", name = "enabled", havingValue = "true")
@Import({Swagger2DocumentationConfiguration.class})
public class SwaggerConfig {
    private static final String VERSION = "1.0.0";

    private final SwaggerProperties swaggerProperties;

    public SwaggerConfig(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    /**
     * 创建API
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .contact(new Contact("JAVA日知录", "http://javadaily.cn", "jianzh5@163.com"))
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .version(VERSION)
                .build();
    }
}
