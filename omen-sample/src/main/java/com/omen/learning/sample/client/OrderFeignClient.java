package com.omen.learning.sample.client;

import com.omen.learning.common.entity.BillDTO;
import com.omen.learning.sample.config.FeignConfig;
import com.omen.learning.sample.support.DemoExcelDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author suxing.zhang
 * @date 2021/3/8 22:24
 **/
@FeignClient(value = "orderService", url = "localhost:8080/test/1", configuration = FeignConfig.class)
public interface OrderFeignClient {
    @GetMapping("/test")
    ResponseEntity<String> getOrder(@RequestParam(value = "id") String id);

    @PostMapping("/test1/1")
    ResponseEntity<BillDTO> saveOrder(@RequestBody DemoExcelDTO demoExcelDTO, @RequestHeader(name = "token") String token);
}
