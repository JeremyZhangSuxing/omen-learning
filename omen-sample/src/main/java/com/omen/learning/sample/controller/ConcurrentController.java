package com.omen.learning.sample.controller;

import com.omen.learning.common.BillDTO;
import com.omen.learning.email.support.EmailHelper;
import com.omen.learning.sample.concurrent.PoolTestService;
import com.omen.learning.sample.scan.ScanService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    private final EmailHelper emailHelper;
    private final ScanService scanService;

    @GetMapping("/bill")
    public List<String> getString() {
        return poolTestService.batchGetBillInfo(BillDTO.buildParam());
    }

    /**
     * 踩坑记录：热部署时静态文件没有更新，本地demo变更了配置一定要clear资源文件
     */
    @PostMapping("email")
    public String emailSend(@RequestBody Reque reque) {
        emailHelper.sendHtml(reque.getSubject(), reque.getUrl());
        return "success";
    }

    @GetMapping("/scan")
    public String scan() {
        return scanService.test();
    }

    @Data
    private static class Reque {
        private String subject;
        private String url;
    }
}
