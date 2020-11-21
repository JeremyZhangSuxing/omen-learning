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
public class PDFUtil {
    /**
     * 生成 PDF 文件
     *
     * @param out  输出流
     * @param html HTML字符串
     * @throws IOException       IO异常
     * @throws DocumentException Document异常
     */
    public static void createPDF(OutputStream out, String html) throws Exception {
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        // 解决中文支持问题
        ITextFontResolver fontResolver = renderer.getFontResolver();
//        ClassPathResource classPathResource = new ClassPathResource("font/FangSong_GB2312.TTF");
//        String path = classPathResource.getURL().getPath();
        fontResolver.addFont("font/FangSong_GB2312.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.layout();
        renderer.createPDF(out);
    }
}
