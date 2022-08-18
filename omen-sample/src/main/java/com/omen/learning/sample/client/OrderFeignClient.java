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

import java.util.List;
import java.util.Map;

/**
 * @author suxing.zhang
 * @date 2021/3/8 22:24
 **/
@FeignClient(value = "orderService", url = "http://localhost:8085/api/v1", configuration = FeignConfig.class)
public interface OrderFeignClient {
    @GetMapping("/products")
    String processProduct();


    @PostMapping("/test/put")
    String testPost();


    @PostMapping("/test1/1")
    ResponseEntity<BillDTO> saveOrder(@RequestBody DemoExcelDTO demoExcelDTO, @RequestHeader(name = "token") String token);

    /**
     * 当需要在feign 中动态传header时，不使用 interceptor (interceptor 如果使用不当会造成污染，按照官方指定的方式配置没啥问题)
     * 因此建议在 feign 对应的facade当中 动态配置
     * @param map
     * @return
     */
    @GetMapping("/list")
    List<BillDTO> fetchBills(@RequestHeader Map<String,String> map);
}
