package com.omen.learning.sample.provider;

import com.omen.learning.common.enums.BillCommonTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author suxing.zhang
 * @date 2021/7/18 15:21
 **/
@Component
@RequiredArgsConstructor
public class BillNoManager {
    private static final String BILL_NO_CACHE_PREFIX = "create_billNo_";

    private final StringRedisTemplate stringRedisTemplate;

    public String generateBillNo(String orderNo, BillCommonTypeEnum billCommonType) {
        //生成递增单号
        Long billNo = stringRedisTemplate.opsForValue().increment(BILL_NO_CACHE_PREFIX + orderNo);
        //生成4位长度string,不足补0
        String billNoStr = String.format("%03d", billNo);
        return orderNo.substring(5) + "-" + billCommonType.getPrefix() + billNoStr;
    }
}