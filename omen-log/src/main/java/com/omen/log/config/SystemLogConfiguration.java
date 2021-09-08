package com.omen.log.config;

import com.omen.log.support.SysLogAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Knight
 * @date : 2021/9/7 11:28 上午
 */
@Configuration
@ConditionalOnProperty(prefix = "omen.log", name = "enabled", havingValue = "true")
public class SystemLogConfiguration {

    @Bean
    public SysLogAspect controllerLogAspect() {
        return new SysLogAspect();
    }
}
