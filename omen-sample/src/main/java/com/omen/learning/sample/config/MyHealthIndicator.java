package com.omen.learning.sample.config;

import com.weweibuy.framework.common.core.exception.Exceptions;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("knightHealthIndicator")
public class MyHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // perform some specific health check
        int errorCode = check();
        if (errorCode == 0) {
            return build();
        }
        return Health.up().build();
    }

    private Health build() {
        return Health.down()
                .withDetail("msg", "error service")
                .withDetail("code", "500")
                .withException(Exceptions.badRequestParam())
                .build();
    }

    /**
     * 此处可对项目启动时 自定义一些健康检查操作
     */
    private int check() {
        return 0;
    }
}