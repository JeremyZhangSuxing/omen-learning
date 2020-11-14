package com.omen.learning.email.support;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
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
            Map<String, String> dataMap = new HashMap<>();
            dataMap.put("title", "用户名激活");
            dataMap.put("url", "233");
            Context context = new Context();
            context.setVariables(Collections.unmodifiableMap(dataMap));
            String emailText = templateEngine.process("email", context);
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