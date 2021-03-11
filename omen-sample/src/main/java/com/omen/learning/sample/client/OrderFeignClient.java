package com.omen.learning.sample.client;

import com.omen.learning.common.entity.BillDTO;
import com.omen.learning.sample.config.FeignConfig;
import com.omen.learning.sample.support.DemoExcelDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author suxing.zhang
 * @date 2021/3/8 22:24
 **/
@FeignClient(value = "orderService",url = "localhost:8080/test/1",configuration = FeignConfig.class)
public interface OrderFeignClient {
    /**
     * test
     * @return
     */
    @GetMapping("/test")
    ResponseEntity<String> getOrder(@RequestParam(value = "id") String id);

    @PostMapping("/test1/1")
    ResponseEntity<BillDTO> saveOrder(@RequestBody DemoExcelDTO demoExcelDTO, @RequestHeader(name = "token") String token);
}
