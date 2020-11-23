package com.omen.learning.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Random;

/**
 * @author 张向兵
 */
@Component
@Slf4j
public class GeneratePdf {

    private static final Random random = new Random();

    public File parseHTML2PDFOutputStream(String content, String pdf) throws Exception {

        String fileName = System.currentTimeMillis() + "-" + random.nextInt(1000);
        String htmlFileName = fileName + ".html";
        try (PrintWriter out = new PrintWriter(htmlFileName,"utf-8")) {
            out.println(pdf);
        }

        File htmlFile = new File(htmlFileName);

        String command = MessageFormat.format("wkhtmltopdf -d 400 --disable-smart-shrinking {0} {1}", htmlFileName, fileName + ".pdf");
        ProcessBuilder pb = new ProcessBuilder(command.split(" "));
        pb.directory(htmlFile.getParentFile()).redirectErrorStream(true);
        Process p = pb.start();
        p.waitFor();

        File pdfFile = new File(fileName + ".pdf");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (FileInputStream fis = new FileInputStream(pdfFile)) {
            StreamUtils.copy(fis, bos);
        }

        //todo 文件上传到oss
        log.info("邮件发送");

        htmlFile.delete();

        return pdfFile;
    }

}