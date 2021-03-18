package com.omen.learning.sample;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author : Knight
 * @date : 2021/3/3 4:01 下午
 */
public class Test {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File("/Users/suxingzhang/Desktop/test/WechatIMG46.png"));
        MagicMatch match = Magic.getMagicMatch(tobyte(fileInputStream));
        System.out.println(match.getMimeType());
    }

    public static byte[] tobyte(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] by = new byte[1024];
        int count;
        while ((count = in.read(by, 0, 1024)) != -1) {
            out.write(by, 0, count);
        }
        return out.toByteArray();

    }

}
