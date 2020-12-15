package com.omen.learning.sample.controller;

import com.omen.learning.common.BillDTO;
import com.omen.learning.email.support.EmailHelper;
import com.omen.learning.sample.concurrent.PoolTestService;
import com.omen.learning.sample.service.scan.ScanService;
import com.omen.learning.sample.test.TestService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

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

    @Value("${test.mock}")
    private Set<String> mockList;

    @GetMapping("/bill")
    public List<String> getString() {
        return poolTestService.batchGetBillInfo(BillDTO.buildParam());
    }

    /**
     * 踩坑记录：热部署时静态文件没有更新，本地demo变更了配置一定要clear资源文件
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
    public String sendHtmlEmail(@RequestBody Reque reque) throws Exception {
        String a = "\n" +
                "<!DOCTYPE html>\n" +
                "<html lang=\"en\" xmlns:th=\"http://www.thymeleaf.org\">\n" +
                "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n" +
                "    <title>move out amount</title>\n" +
                "\n" +
                "    <style>\n" +
                "        @page {\n" +
                "            /*size: 10.42in 7.42in;*/\n" +
                "            margin: 0;\n" +
                "            /*content: counter(page);*/\n" +
                "            width: 100%;\n" +
                "            height: 100%;\n" +
                "        }\n" +
                "\n" +
                "        /*不能随意修改这里 hongwei.lian*/\n" +
                "\n" +
                "        * {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "\n" +
                "        html, body {\n" +
                "            font-family: \"SimSun\", serif;\n" +
                "            font-size: 14pt;\n" +
                "            width: 100%;\n" +
                "            height: 100%;\n" +
                "            /*background:url(/data/App/ptoms.cfaoe.local/logo-bg.jpg);*/\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<div style=\"color: rgba(0, 0, 0, 0.85); font-size: 14px; min-width: 600px; overflow: auto;\">\n" +
                "    <div style=\"text-align: center;  height: 100px; line-height: 100px; border-bottom: 1px solid rgb(232, 232, 232);\">\n" +
                "        <span style=\"color: rgb(43, 43, 51); font-size: 32px; font-weight: 600;\">WeWork</span>\n" +
                "    </div>\n" +
                "    <div style=\"padding: 24px;\">\n" +
                "        <div style=\" margin-bottom: 32px;\">\n" +
                "            <h3 style=\"color: rgb(43, 43, 51); font-size: 24px; font-weight: 600;\">Account confirmation for SR\n" +
                "                refund</h3>\n" +
                "            <div style=\"padding: 16px 0 0 0;\">The office you rent in WeWork has entered the process of moving out,\n" +
                "                please confirm the Account for SR refund.\n" +
                "            </div>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"body__content\">\n" +
                "            <table style=\"width: 100%; border-collapse:collapse;\">\n" +
                "                <thead style=\"height: 54px; line-height: 54px; text-align: left; background-color: #fafafa; border-bottom: 1px solid #ccc;\">\n" +
                "                <th style=\"padding: 0 8px; border-bottom: 1px solid rgb(240, 240, 240)\">Location Name</th>\n" +
                "                <th style=\"padding: 0 8px; border-bottom: 1px solid rgb(240, 240, 240)\">Office / Deck</th>\n" +
                "                </thead>\n" +
                "                <tbody>\n" +
                "                <tr style=\"line-height: 54px;\" th:each=\"userInfo,userInfoStat : ${userInfoList}\">\n" +
                "                    <td style=\"padding: 0 8px; border-bottom: 1px solid rgb(240, 240, 240)\"\n" +
                "                        th:text=\"${locationName}\"></td>\n" +
                "                    <td style=\"padding: 0 8px; border-bottom: 1px solid rgb(240, 240, 240)\" th:text=\"${skuName}\"></td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "\n" +
                "    <div style=\"text-align: center;\">\n" +
                "        <a style=\"display: inline-block; cursor: pointer; padding: 6px 12px; border-radius: 2px; border: 1px solid rgb(24, 144, 255); color: #fff; background: rgb(24, 144, 255);\">\n" +
                "            Confirm\n" +
                "        </a>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "<div style=\"color: rgba(0, 0, 0, 0.85); font-size: 14px; min-width: 600px; overflow: auto;\">\n" +
                "    <div style=\"text-align: center;  height: 100px; line-height: 100px; border-bottom: 1px solid rgb(232, 232, 232);\">\n" +
                "        <span style=\"color: rgb(43, 43, 51); font-size: 32px; font-weight: 600;\">WeWork</span>\n" +
                "    </div>\n" +
                "    <div style=\"padding: 24px;\">\n" +
                "        <div style=\" margin-bottom: 32px;\">\n" +
                "            <h3 style=\"color: rgb(43, 43, 51); font-size: 24px; font-weight: 600;\">WeWork搬出服務保證金賬戶確認</h3>\n" +
                "            <div style=\"padding: 16px 0 0 0;\">您在WeWork租用的辦公室（如下）已經進入退租流程，請務必確認服務保證金退回的賬戶</div>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"body__content\">\n" +
                "            <table style=\"width: 100%; border-collapse:collapse;\">\n" +
                "                <thead style=\"height: 54px; line-height: 54px; text-align: left; background-color: #fafafa; border-bottom: 1px solid #ccc;\">\n" +
                "                <th style=\"padding: 0 8px; border-bottom: 1px solid rgb(240, 240, 240)\">樓宇名稱</th>\n" +
                "                <th style=\"padding: 0 8px; border-bottom: 1px solid rgb(240, 240, 240)\">Office / 工位編號</th>\n" +
                "                </thead>\n" +
                "                <tbody>\n" +
                "                <tr style=\"line-height: 54px;\" th:each=\"userInfo,userInfoStat : ${userInfoList}\">\n" +
                "                    <td style=\"padding: 0 8px; border-bottom: 1px solid rgb(240, 240, 240)\" th:text=\"${locationName}\">\n" +
                "                        中海国际中心\n" +
                "                    </td>\n" +
                "                    <td style=\"padding: 0 8px; border-bottom: 1px solid rgb(240, 240, 240)\" th:text=\"${skuName}\">办公室\n" +
                "                        LD220\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "\n" +
                "    <div style=\"text-align: center;\">\n" +
                "        <a style=\"display: inline-block; cursor: pointer; padding: 6px 12px; border-radius: 2px; border: 1px solid rgb(24, 144, 255); color: #fff; background: rgb(24, 144, 255);\">去確認</a>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n" +
                "\n";
        emailHelper.sendEmail(a);
        return "发送成功";
    }

}
