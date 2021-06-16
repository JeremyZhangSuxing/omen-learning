package com.omen.learning.sample;

import com.omen.learning.bean.test.annotattion.EnableBeanRegistry;
import com.omen.learning.common.annotation.EnableTokenValidate;
import com.omen.learning.sample.mybatis.mapper.UploadRecordMapper;
import com.weweibuy.framework.rocketmq.annotation.EnableRocket;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author : Knight
 * @date : 2020/11/8 10:57 上午
 */
@EnableTokenValidate
@EnableBeanRegistry
@EnableCaching
@EnableFeignClients
@EnableAsync
@MapperScan(basePackageClasses = UploadRecordMapper.class)
@SpringBootApplication
@EnableRocket
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
