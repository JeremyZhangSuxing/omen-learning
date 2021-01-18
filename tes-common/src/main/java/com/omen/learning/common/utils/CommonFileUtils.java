package com.omen.learning.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author : Knight
 * @date : 2020/12/15 1:30 下午
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonFileUtils {

    public static String standardFileName(String name) throws UnsupportedEncodingException {
        return URLEncoder.encode(name, "UTF-8").replace("\\+", "%20");
    }

    public static String standardFileName1(String name) throws UnsupportedEncodingException {
        return URLEncoder.encode(name, StandardCharsets.ISO_8859_1.displayName()).replace("\\+", "%20");
    }

}
