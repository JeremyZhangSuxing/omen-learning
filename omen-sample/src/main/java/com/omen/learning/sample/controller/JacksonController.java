package com.omen.learning.sample.controller;

import com.omen.learning.common.entity.JackSonDemo;
import com.omen.learning.common.support.JackJsonProUtils;
import com.weweibuy.framework.common.core.model.dto.CommonDataResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author suxing.zhang
 * @date 2021/2/1 21:03
 **/
@Slf4j
@RestController
@RequestMapping("/jackson")
public class JacksonController {

    @PostMapping("/json")
    public CommonDataResponse<JackSonDemo> testJackSon(@RequestBody JackSonDemo jackSonDemo) {
        log.info("test data {}", jackSonDemo);
        return CommonDataResponse.success(JackSonDemo.buildOneObject());
    }

    @GetMapping("/json/list")
    public CommonDataResponse<List<JackSonDemo>> testJackSon1() {
        String key = JackJsonProUtils.convertToJson(JackSonDemo.buildManyObject());
        log.info("test data {}", key);
        List<JackSonDemo> jackSonDemos = JackJsonProUtils.convertToList(key, JackSonDemo.class);
        return CommonDataResponse.success(jackSonDemos);
    }

}
