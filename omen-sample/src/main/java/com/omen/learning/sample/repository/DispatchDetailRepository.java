package com.omen.learning.sample.repository;

import com.omen.learning.sample.mybatis.mapper.DispatchBillDetailMapper;
import com.omen.learning.sample.mybatis.po.DispatchBillDetail;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author suxing.zhang
 * @date 2021/7/24 12:15
 **/
@Repository
@RequiredArgsConstructor
public class DispatchDetailRepository {
    private final DispatchBillDetailMapper dispatchBillDetailMapper;
    private final SqlSessionFactory sqlSessionFactory;

    public void saveAll(List<DispatchBillDetail> details) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try {
            for (DispatchBillDetail detail : details) {
                if (detail.getSku().equals("2")) {
                    detail.setSku(null);
                }
                dispatchBillDetailMapper.insertSelective(detail);
            }
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

}
