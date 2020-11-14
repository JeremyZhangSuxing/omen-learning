package com.omen.learning.sample.controller;

import com.omen.learning.common.BillDTO;
import com.omen.learning.email.support.EmailHelper;
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
    private final EmailHelper emailHelper;

    @GetMapping("/bill")
    public List<String> getString() {
        return poolTestService.batchGetBillInfo(BillDTO.buildParam());
    }

    /**
     * 踩坑记录：热部署时静态文件没有更新，本地demo变更了配置一定要clear资源文件
     */
    @GetMapping("email")
    public String emailSend() {
        emailHelper.sendHtml("2");
        return "success";
    }
}
