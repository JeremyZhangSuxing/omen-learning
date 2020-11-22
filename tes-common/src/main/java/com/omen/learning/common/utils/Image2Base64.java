package com.omen.learning.common.utils;


import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

/**
 * @author zhang.suxing
 * @date 2020/11/22 17:40
 **/
public class Image2Base64 {

    /***
     *
     * 将图片转换为Base64<br>
     * 将base64编码字符串解码成img图片
     * @param imgFile
     * @return
     */
    public static String getImgStr(String imgFile) {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            // 创建URL
            URL url = new URL(imgFile);
            byte[] by = new byte[1024];
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            InputStream is = conn.getInputStream();
            // 将内容放到内存中
            int len = -1;
            while ((len = is.read(by)) != -1) {
                data.write(by, 0, len);
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        return Base64.encodeBase64String(data.toByteArray());
    }


    /**
     * 本地图片转换Base64的方法
     *
     * @param imgPath
     */

    private static void ImageToBase64(String imgPath) {
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码

        // 返回Base64编码过的字节数组字符串
        System.out.println("本地图片转换Base64:" + "data:image/png;base64," + Base64.encodeBase64String(Objects.requireNonNull(data)));
    }

    public static void main(String[] args) throws Exception {
        String str = getImgStr("http://chuantu.xyz/t6/741/1605957578x1700340463.png");
        ImageToBase64("D:/pdf/img_1.png");
    }

}
