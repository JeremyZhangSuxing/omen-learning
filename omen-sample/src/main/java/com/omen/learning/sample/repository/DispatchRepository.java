package com.omen.learning.sample.repository;

import com.omen.learning.sample.mybatis.po.DispatchBillDetail;
import com.omen.learning.sample.mybatis.po.DispatchBillInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author suxing.zhang
 * @date 2021/7/24 12:33
 **/
@Repository
@RequiredArgsConstructor
public class DispatchRepository {
    private final DispatchInfoRepository dispatchInfoRepository;
    private final DispatchDetailRepository dispatchDetailRepository;

    @Transactional
    public void saveDispatch(DispatchBillInfo dispatchBillInfo, List<DispatchBillDetail> details) {
        dispatchInfoRepository.save(dispatchBillInfo);
        dispatchDetailRepository.saveAll(details);
    }
}
