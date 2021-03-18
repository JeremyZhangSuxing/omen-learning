package com.omen.learning.sample.test;

import com.omen.learning.sample.mybatis.example.CmOrderExample;
import com.omen.learning.sample.mybatis.mapper.CmOrderMapper;
import com.omen.learning.sample.mybatis.po.CmOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhang.suxing
 * @date 2020/11/12 21:41
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class TestService {
    private final CmOrderMapper cmOrderMapper;

    public CmOrder listOrders(Long id) {
        CmOrderExample example = new CmOrderExample();
        example.createCriteria().andIdEqualTo(1L);
        return cmOrderMapper.selectOneByExample(example);
    }

    public CmOrder updateOrder(CmOrder cmOrder) {
        CmOrderExample example = new CmOrderExample();
        example.createCriteria().andIdEqualTo(1L);
        cmOrderMapper.updateByExampleSelective(cmOrder, example);
        return cmOrderMapper.selectOneByExample(example);
    }

    public CmOrder saveOrder(CmOrder cmOrder) {
        log.info("数据库入库成功");
        return cmOrder;
    }
}
