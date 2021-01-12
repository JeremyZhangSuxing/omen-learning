package com.omen.learning.sample.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author : Knight
 * @date : 2021/1/12 11:42 上午
 */
@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String tid = "omen-member";

    /**
     * JWT 生成密钥使用的密码
     */
    private String jutRule = "Jeremy";
    /**
     * 过期时间30天
     */
    private long expiration30Day = 1000 * 60 * 60 * 24L;

}
