package com.omen.learning.sample.controller;

import com.omen.learning.common.entity.SampleDog;
import com.omen.learning.common.entity.SampleUser;
import com.omen.learning.sample.mq.SampleProvider;
import com.weweibuy.framework.common.core.model.dto.CommonDataResponse;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Knight
 * @date : 2021/6/16 4:00 下午
 */
@RestController
@RequestMapping("/api/mq")
@RequiredArgsConstructor
public class RocketMqController {
    private final SampleProvider sampleProvider;

    @GetMapping("/hello")
    public Object hello(String msg, String tag, String key) {
        SampleUser<SampleDog> user = user(msg);
        user.setSampleDog(dog());
        SendResult send = sampleProvider.send(user, tag, key);
        return CommonDataResponse.success(send);
    }


    private static SampleUser user(String name) {
        SampleUser sampleUser = new SampleUser();
        sampleUser.setUserName(name);
        sampleUser.setAge(12);
//        LocalDateTime of = LocalDateTime.of(1990, 12, 12, 21, 22);
//        sampleUser.setBirthday(of);
        return sampleUser;
    }

    private static SampleDog dog() {
        SampleDog sampleDog = new SampleDog();
        sampleDog.setDogName("dog tom");
        sampleDog.setDogAge(11);
        return sampleDog;
    }
}
