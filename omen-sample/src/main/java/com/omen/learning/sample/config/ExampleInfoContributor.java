package com.omen.learning.sample.config;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class ExampleInfoContributor implements InfoContributor {

    /**
     * key-value 形式返回信息
     */
    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("example",
                Collections.singletonMap("key", "value"));
    }

}