package com.omen.learning.email.support;

import com.omen.learning.common.utils.PdfUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

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

    public void sendHtml(String subject, String url) {
        try {
            //获取生成的模板
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("title", "您好！这是一封来自jeremy的测试邮件！之后，您会不定=定收到类似的邮件，可选择取消订阅！感谢您的支持！");
            dataMap.put("msg", "您好！这是测试邮件的内容，请直接忽略即可！");
            dataMap.put("orderId", "123");
            Context context = new Context();
            context.setVariables(Collections.unmodifiableMap(dataMap));
            String emailText = templateEngine.process("email", context);
            FileOutputStream fileOutputStream = new FileOutputStream("D:/pdf/orderItemUuid113.pdf");
            PdfUtil.createPDF(fileOutputStream, emailText, url);
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
            FileSystemResource resource = new FileSystemResource(new File("F:/0404workspace/omen-learning/omen-sample/src/main/resources/images/img_1.png"));
            helper.addInline("img_1", resource);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * images resource 存放于message 中不通用
     */
    public void sendMailWithInline(final String recipientName, final String recipientEmail, final String imageResourceName,
                                   final byte[] imageBytes, final String imageContentType, final Locale locale)
            throws MessagingException {

        // Prepare the evaluation context
        final Context ctx = new Context(locale);
        ctx.setVariable("name", recipientName);
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
        // so that we can reference it from HTML
        ctx.setVariable("imageResourceName", imageResourceName);
        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // true = multipart
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        message.setSubject("Example HTML email with inline image");
        message.setFrom("thymeleaf@example.com");
        message.setTo(recipientEmail);
        // Create the HTML body using Thymeleaf
        final String htmlContent = this.templateEngine.process("email-inlineimage.html", ctx);
        // true = isHtml
        message.setText(htmlContent, true);
        // Add the inline image, referenced from the HTML code as "cid:${imageResourceName}"
        final InputStreamSource imageSource = new ByteArrayResource(imageBytes);
        message.addInline(imageResourceName, imageSource, imageContentType);
        // Send mail
        javaMailSender.send(mimeMessage);

    }
}