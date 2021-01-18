package com.omen.learning.sample.test;

import com.omen.learning.sample.mybatis.example.CmOrderExample;
import com.omen.learning.sample.mybatis.mapper.CmOrderMapper;
import com.omen.learning.sample.mybatis.po.CmOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhang.suxing
 * @date 2020/11/12 21:41
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class TestService {
    private final CmOrderMapper cmOrderMapper;

    public List<CmOrder> listOrders() {
        log.info("签名校验通过，开始执行业务逻辑");
        CmOrderExample example = new CmOrderExample();
        example.createCriteria().andIdGreaterThan(1L);
        return cmOrderMapper.selectByExample(example);
    }
}
