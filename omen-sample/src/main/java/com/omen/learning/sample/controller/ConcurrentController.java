package com.omen.learning.sample.controller;

import com.omen.learning.common.BillDTO;
import com.omen.learning.email.support.EmailHelper;
import com.omen.learning.sample.concurrent.PoolTestService;
import com.omen.learning.sample.service.scan.ScanService;
import com.omen.learning.sample.test.TestService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private final EmailHelper emailHelper;
    private final ScanService scanService;
    private final TestService testService;


    @GetMapping("/bill")
    public List<String> getString() {
        return poolTestService.batchGetBillInfo(BillDTO.buildParam());
    }

    /**
     * 踩坑记录：热部署时静态文件没有更新，本地demo变更了资源文件一定要clear资源文件
     */
    @GetMapping("email")
    public String emailSend() {
        emailHelper.sendHtml("test");
        return "success";
    }

    @GetMapping("/scan")
    public String scan() {
        return scanService.test();
    }

    @GetMapping("job")
    public String atomic() throws InterruptedException {

        new Thread(() -> {
            try {
                testService.test();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(50);
                testService.test();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        return "成功";
    }

    @Data
    private static class Reque {
        private String subject;
        private String url;
        private String html;
    }


    @PostMapping("/html")
    public String sendHtmlEmail(@RequestBody Reque reque) {
        return "发送成功";
    }

}
