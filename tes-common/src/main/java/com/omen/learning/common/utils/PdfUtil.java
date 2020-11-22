package com.omen.learning.common.utils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author : Knight
 * @date : 2020/11/19 11:19 下午
 */
public class PdfUtil {
    /**
     * 生成 PDF 文件
     *
     * @param out  输出流
     * @param html HTML字符串
     * @throws IOException       IO异常
     * @throws DocumentException Document异常
     */
    public static void createPDF(OutputStream out, String html, String url) throws Exception {
        ITextRenderer renderer = new ITextRenderer();
        System.out.println(html);
        renderer.getSharedContext().setReplacedElementFactory(new Base64ImgReplacedElementFactory(url));
        renderer.getSharedContext().getTextRenderer().setSmoothingThreshold(0);
        renderer.setDocumentFromString(html);
        // 解决中文支持问题
        ITextFontResolver fontResolver = renderer.getFontResolver();

        fontResolver.addFont("font/simfang.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        fontResolver.addFont("C:/Windows/Fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        fontResolver.addFont("C:/Windows/Fonts/simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        fontResolver.addFont("C:/Windows/Fonts/simkai.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//        renderer.getSharedContext().setBaseURL("file:/D:/z/temp/");
        // 如果img标签中src是http或绝对地址可以不要本行代码,另外对于linux系统中写法暂不确认应该是不需要file:/前缀
        renderer.layout();
        renderer.createPDF(out);

//        ClassPathResource classPathResource = new ClassPathResource("font/FangSong_GB2312.TTF");
//        String path = classPathResource.getURL().getPath();
    }
}
