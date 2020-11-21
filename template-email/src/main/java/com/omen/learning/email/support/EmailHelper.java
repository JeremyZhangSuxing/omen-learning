package com.omen.learning.email.support;

import com.omen.learning.common.utils.PDFUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Knight
 * @date : 2020/11/13 10:45 上午
 */
@Component
@RequiredArgsConstructor
public class EmailHelper {
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String username;

    public void sendHtml( String subject) {
        try {
            //获取生成的模板
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("title", "您好！这是一封来自jeremy的测试邮件！之后，您会不定=定收到类似的邮件，可选择取消订阅！感谢您的支持！");
            dataMap.put("msg","您好！这是测试邮件的内容，请直接忽略即可！");
            dataMap.put("orderId","123");
            Context context = new Context();
            context.setVariables(Collections.unmodifiableMap(dataMap));
            String emailText = templateEngine.process("email", context);
            FileOutputStream fileOutputStream = new FileOutputStream("/Users/suxingzhang/orderItemUuid113.pdf");
            PDFUtil.createPDF(fileOutputStream,emailText);
            //消息处理助手对象
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject(subject);
            //设置发件人
            helper.setFrom(username);
            //设置收件人
            helper.setTo("1264677205@qq.com");
            //设置邮件标题
            helper.setSubject("主题：用户名激活");
            //设置邮件内容 ，true 表示发送html 格式
            helper.setText(emailText, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}