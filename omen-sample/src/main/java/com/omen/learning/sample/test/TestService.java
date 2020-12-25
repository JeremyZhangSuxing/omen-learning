package com.omen.learning.sample.test;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zhang.suxing
 * @date 2020/11/12 21:41
 **/
@Service
public class TestService {
    public static void timeToStr(){
        LocalDateTime dateTime = LocalDateTime.now();
        //使用预定义实例来转换
        DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE;
        String dateStr = dateTime.format(fmt);
        System.out.println("LocalDateTime转String[预定义]:"+dateStr);
    }
}
