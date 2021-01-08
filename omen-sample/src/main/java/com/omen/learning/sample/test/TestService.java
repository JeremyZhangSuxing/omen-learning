package com.omen.learning.sample.test;

import com.omen.learning.sample.mybatis.example.CmOrderExample;
import com.omen.learning.sample.mybatis.mapper.CmOrderMapper;
import com.omen.learning.sample.mybatis.po.CmOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author zhang.suxing
 * @date 2020/11/12 21:41
 **/
@Service
@RequiredArgsConstructor
public class TestService {
    private final CmOrderMapper cmOrderMapper;


    public static void timeToStr() {
        LocalDateTime dateTime = LocalDateTime.now();
        //使用预定义实例来转换
        DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE;
        String dateStr = dateTime.format(fmt);
        System.out.println("LocalDateTime转String[预定义]:" + dateStr);
    }


    public List<CmOrder> listOrders() {
        CmOrderExample example = new CmOrderExample();
        example.createCriteria().andIdGreaterThan(1L);
        return cmOrderMapper.selectByExample(example);
    }
}
