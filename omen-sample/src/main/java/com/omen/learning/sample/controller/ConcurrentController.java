package com.omen.learning.sample.controller;

import com.omen.learning.common.BillDTO;
import com.omen.learning.sample.concurrent.PoolTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Knight
 * @date : 2020/11/8 3:37 下午
 */
@RestController
@RequestMapping("/concurrent")
@RequiredArgsConstructor
public class ConcurrentController {
    private final PoolTestService poolTestService;
    
    @GetMapping("/bill")
    public List<String> getString() {
        return poolTestService.batchGetBillInfo(BillDTO.buildParam());
    }
}
