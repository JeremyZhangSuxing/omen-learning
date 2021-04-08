package com.omen.learning.common.support;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * @author : Knight
 * @date : 2021/1/6 1:10 下午
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileHelper {
    /**
     * 创建临时文件
     */
    public static File getTempFile(String url) throws IOException {
        //对本地文件命名
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        File file = File.createTempFile("net-", "." + fileName);
        URL urlFile = new URL(url);
        try (InputStream inStream = urlFile.openStream();
             OutputStream os = new FileOutputStream(file)) {
            int bytesRead;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inStream.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            log.error("文件流转化异常，{}", e.getMessage());
        }
        return file;
    }
}
