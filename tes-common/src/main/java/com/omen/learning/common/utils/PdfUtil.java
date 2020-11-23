package com.omen.learning.common.utils;


import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.TimeZone;

/**
 * @author : Knight
 * @date : 2020/11/19 11:19 下午
 */
@Component
public class PdfUtil {
    private static final String font_path = "/Users/suxingzhang/Desktop/font/simsun.ttc";
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Autowired
    private GeneratePdf generatePdf;

    /**
     * 生成 PDF 文件
     *
     * @param out  输出流
     * @param html HTML字符串
     * @throws IOException IO异常
     * @throws Exception   Document异常
     */
    public static void createPDF(OutputStream out, String html, String url) throws Exception {
        System.out.println(html);
        ITextRenderer renderer = new ITextRenderer();
        renderer.getSharedContext().setReplacedElementFactory(new Base64ImgReplacedElementFactory(url));
        renderer.getSharedContext().getTextRenderer().setSmoothingThreshold(0);
        renderer.setDocumentFromString(html);
        // 解决中文支持问题
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont(font_path, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.layout();
        renderer.createPDF(out);
    }

    public static void convertHtml(FileOutputStream outputStream,String html) throws IOException, DocumentException {
        ITextRenderer renderer = new ITextRenderer();
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont(font_path, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);
    }

    /**
     * 根据页面生成pdf文件
     *
     * @param uuid
     * @return File
     * @throws Exception Exception
     */
    public File generateInvoiceBillPdf(String uuid) throws Exception {
        Configuration cfg = freeMarkerConfigurer.getConfiguration();

        StringWriter stringWriterContent = new StringWriter();
        Template template = cfg.getTemplate("email.ftl", "utf-8");
        Environment environment = template.createProcessingEnvironment(null, stringWriterContent, null);
        environment.setTimeZone(TimeZone.getDefault());
        environment.process();


        StringWriter stringWriterPdfContent = new StringWriter();
        Template templatePdf = cfg.getTemplate("invoicebillpdf.ftl", "utf-8");
        Environment environmentPdf = templatePdf.createProcessingEnvironment(null, stringWriterPdfContent, null);
        environmentPdf.setTimeZone(TimeZone.getDefault());
        environmentPdf.process();

        return generatePdf.parseHTML2PDFOutputStream(stringWriterContent.toString(), stringWriterPdfContent.toString());
    }
}
