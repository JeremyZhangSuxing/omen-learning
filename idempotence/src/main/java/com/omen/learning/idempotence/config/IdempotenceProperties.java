package com.omen.learning.idempotence.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.Set;

/**
 * @author : Knight
 * @date : 2020/12/1 6:15 下午
 */
@Data
@ConfigurationProperties(prefix = "idempotence")
public class IdempotenceProperties {

    /**
     * 幂等策略
     */
    private String managerType;
    /**
     * 幂等表name
     */
    private String tableName;
    /**
     * 配置文件已=可以用collect接受 格式 1,2,3...
     */
    private Set<String> mockList = Collections.singleton("mock_test1");
}
