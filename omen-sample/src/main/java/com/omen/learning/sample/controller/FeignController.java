package com.omen.learning.sample.controller;

import com.omen.learning.common.entity.BillDTO;
import com.omen.learning.sample.client.OrderFeignClient;
import com.omen.learning.sample.support.BillType;
import com.omen.learning.sample.support.DemoExcelDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suxing.zhang
 * @date 2021/3/8 22:28
 **/
@RestController
@RequiredArgsConstructor
public class FeignController {

    private final OrderFeignClient orderFeignClient;

    @GetMapping("/order")
    public String getOrder(@RequestParam(name = "id") String id) {
        try {
            ResponseEntity<String> order = orderFeignClient.getOrder(id);
            return order.getBody();
        } catch (Exception e) {
            return "success";
        }
    }

    @GetMapping("/getOrder")
    public String getOder(@RequestParam(name = "id") String id) {
        try {
            DemoExcelDTO demoExcelDTO = new DemoExcelDTO();
            demoExcelDTO.setBillType(BillType.B2C);
            demoExcelDTO.setDispatchDate(99999L);
            demoExcelDTO.setUsername("jeremy");
            ResponseEntity<BillDTO> jeremy = orderFeignClient.saveOrder(demoExcelDTO, "jeremy");
            return jeremy.getBody().getId().toString();
        } catch (Exception e) {
            return "success";
        }
    }
}
