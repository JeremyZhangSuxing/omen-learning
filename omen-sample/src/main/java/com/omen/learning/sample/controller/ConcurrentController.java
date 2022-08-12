package com.omen.learning.sample.controller;

import com.google.common.collect.Lists;
import com.omen.learning.common.entity.BillDTO;
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
        return poolTestService.batchGetBillInfo(Lists.newArrayList());
    }

    /**
     * 踩坑记录：热部署时静态文件没有更新，本地demo变更了资源文件一定要clear资源文件
     */
    @GetMapping("/email")
    public String emailSend() {
        return "成功";
    }
}
