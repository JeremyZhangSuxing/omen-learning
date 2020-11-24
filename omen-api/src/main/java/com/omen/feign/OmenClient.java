package com.omen.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhang.suxing
 * @date 2020/11/23 22:32
 **/
@FeignClient(value = "omenService")
public interface OmenClient {
    @GetMapping
    String testFeign(@RequestParam String param);
}
