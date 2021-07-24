package com.omen.learning.sample.repository;

import com.omen.learning.sample.mybatis.mapper.DispatchBillInfoMapper;
import com.omen.learning.sample.mybatis.po.DispatchBillInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author suxing.zhang
 * @date 2021/7/24 12:13
 **/
@Repository
@RequiredArgsConstructor
public class DispatchInfoRepository {
    private final DispatchBillInfoMapper dispatchBillInfoMapper;

    public void save(DispatchBillInfo dispatchBillInfo) {
        dispatchBillInfoMapper.insertSelective(dispatchBillInfo);
    }
}
