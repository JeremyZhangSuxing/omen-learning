package com.omen.learning.common.utils;


import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author : Knight
 * @date : 2020/11/19 11:19 下午
 */
public class PdfUtil {
    private static final String FONT_PATH = "font/simsun.ttc";

    /**
     * html转化为pdf文件
     * @param outputStream pdf流
     * @param html html
     * @throws IOException exception
     * @throws DocumentException DocumentException
     */
    public static void convertHtml(FileOutputStream outputStream, String html) throws IOException, DocumentException {
        ITextRenderer renderer = new ITextRenderer();
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);
    }
}
