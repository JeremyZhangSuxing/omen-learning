package com.omen.learning.common.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author zhang.suxing
 * @date 2020/11/22 17:40
 **/
@Slf4j
public class Image2Base64 {

    /***
     *
     * 将图片转换为Base64<br>
     * 将base64编码字符串解码成img图片
     * @param imgFile imgUrl
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
     * 本地图片转换Base64的方法  png格式
     */
    private static String image2Base64Code(String imgPath) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        byte[] data;
        // 读取图片字节数组
        File file = new File(imgPath);
        String extension = FilenameUtils.getExtension(file.getName());
        try (InputStream in = new FileInputStream(imgPath)) {
            data = new byte[1024];
            int len;
            while ((len = in.read(data)) != -1) {
                stringBuilder.append(new String(data, 0, len));
            }
            return "data:image/" + extension + ";base64," + Base64.encodeBase64String(stringBuilder.toString().getBytes());
        } catch (IOException e) {
            log.error("图片转化未base64异常 ", e);
            // 返回Base64编码过的字节数组字符串
            return "";
        }
    }

}
