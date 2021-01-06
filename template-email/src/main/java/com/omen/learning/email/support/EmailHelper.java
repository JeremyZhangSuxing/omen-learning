package com.omen.learning.email.support;

import com.omen.learning.common.entity.CustomerInfoVO;
import com.weweibuy.framework.common.core.exception.BusinessException;
import com.weweibuy.framework.common.core.model.eum.CommonErrorCodeEum;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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

    public void sendHtml(String subject) {
        try {
            //获取生成的模板
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("title", "您好！这是一封来自jeremy的测试邮件！之后，您会不定=定收到类似的邮件，可选择取消订阅！感谢您的支持！");
            dataMap.put("msg", "您好！这是测试邮件的内容，请直接忽略即可！");
            dataMap.put("customerInfoList", CustomerInfoVO.buildList());
            dataMap.put("sitrue", "http://www.baidu.com");
            String imageSource = "https://wework-chinaos.oss-cn-shanghai.aliyuncs.com/36f313e3-cf7c-4a24-9c84-a73f2a610c7e-1608776695386.png";
            dataMap.put("imageResourceName", imageSource);
            Context context = new Context();
            context.setVariables(Collections.unmodifiableMap(dataMap));
            String emailText = templateEngine.process("email", context);
            String path = buildFilePath() + "/order.pdf";
//            FileOutputStream fileOutputStream = new FileOutputStream(path);
//            PdfUtil.convertHtml(fileOutputStream, emailText);
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject(subject);
            //设置发件人
            helper.setFrom(username);
            //设置收件人  chang.zhang4@wework.com
            helper.setTo("suxing.zhang3@wework.com");
            //设置邮件标题
            helper.setSubject("主题：测试邮件");
            //设置邮件内容 ，true 表示发送html 格式
            helper.setText(emailText, true);
//            helper.addAttachment("测试pdf.pdf", new File(path));
            javaMailSender.send(message);
            //send email  delete file
            FileUtils.deleteQuietly(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件路径
     */
    private String buildFilePath() {
        return Optional.ofNullable(Objects.requireNonNull(ClassUtils.getDefaultClassLoader()).getResource(""))
                .orElseThrow(() -> new BusinessException(CommonErrorCodeEum.BAD_REQUEST_PARAM)).getPath();
    }
}