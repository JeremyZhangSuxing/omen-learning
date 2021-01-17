package com.omen.learning.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author : Knight
 * @date : 2020/12/15 1:30 下午
 */
public class CommonFileUtils {

    public static String standardFileName(String name) throws UnsupportedEncodingException {
        return URLEncoder.encode(name, "UTF-8").replace("\\+", "%20");
    }


}
