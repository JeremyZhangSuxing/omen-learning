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

    AtomicBoolean atomicBoolean = new AtomicBoolean(true);

    public void test() throws InterruptedException {
        if (!atomicBoolean.getAndSet(false)){
            System.err.println("job has been scheduled");
            return;
        }

        while (true) {
            Thread.sleep(100);
            System.out.println("this is answer");
        }

    }

    public static void main(String[] args) {
        timeToStr();
    }

    public static void timeToStr(){
        LocalDateTime dateTime = LocalDateTime.now();

        //使用预定义实例来转换
        DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE;
        String dateStr = dateTime.format(fmt);
        System.out.println("LocalDateTime转String[预定义]:"+dateStr);
    }
}
