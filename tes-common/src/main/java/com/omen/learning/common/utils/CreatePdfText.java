package com.omen.learning.common.utils;


import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author : Knight
 * @date : 2020/11/19 9:01 下午
 */
public class CreatePdfText {
    public static void main(String[] args) throws Exception {
        Document document = new Document();
        File file = new File("E:/test.pdf");
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("E:/test.pdf"));
        document.open();
        document.add(new Paragraph("Hello World!"));

        document.close();
        writer.close();
    }
}
