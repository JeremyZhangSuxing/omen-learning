package com.omen.learning.sample.config;

import com.omen.learning.idempotence.config.IdempotenceProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Knight
 * @date : 2020/11/12 1:46 下午
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties({IdempotenceProperties.class})
public class BaseConfig {

//    @Bean
//    public StringHttpMessageConverter stringHttpMessageConverter(){
//        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
//        stringHttpMessageConverter.setDefaultCharset(Charset.defaultCharset());
//        stringHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
//        return stringHttpMessageConverter;
//    }


}
