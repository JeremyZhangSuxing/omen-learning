package com.omen.learning.sample.mybatis.mapper;

import com.omen.learning.sample.mybatis.po.DispatchBillInfo;
import com.omen.learning.sample.mybatis.example.DispatchBillInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DispatchBillInfoMapper {
    long countByExample(DispatchBillInfoExample example);

    int deleteByExample(DispatchBillInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DispatchBillInfo record);

    int insertSelective(DispatchBillInfo record);

    DispatchBillInfo selectOneByExample(DispatchBillInfoExample example);

    DispatchBillInfo selectOneByExampleForUpdate(DispatchBillInfoExample example);

    List<DispatchBillInfo> selectByExampleWithLimit(@Param("example") DispatchBillInfoExample example, @Param("limit") Integer limit);

    List<DispatchBillInfo> selectByExampleForUpdate(DispatchBillInfoExample example);

    List<DispatchBillInfo> selectByExample(DispatchBillInfoExample example);

    DispatchBillInfo selectByPrimaryKeyForUpdate(Long id);

    DispatchBillInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DispatchBillInfo record, @Param("example") DispatchBillInfoExample example);

    int updateByExample(@Param("record") DispatchBillInfo record, @Param("example") DispatchBillInfoExample example);

    int updateByPrimaryKeySelective(DispatchBillInfo record);

    int updateByPrimaryKey(DispatchBillInfo record);
}