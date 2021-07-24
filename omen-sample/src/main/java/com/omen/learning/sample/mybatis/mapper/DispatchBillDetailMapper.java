package com.omen.learning.sample.mybatis.mapper;

import com.omen.learning.sample.mybatis.po.DispatchBillDetail;
import com.weweibuy.framework.samples.mybatis.plugin.model.example.DispatchBillDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DispatchBillDetailMapper {
    long countByExample(DispatchBillDetailExample example);

    int deleteByExample(DispatchBillDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DispatchBillDetail record);

    int insertSelective(DispatchBillDetail record);

    DispatchBillDetail selectOneByExample(DispatchBillDetailExample example);

    DispatchBillDetail selectOneByExampleForUpdate(DispatchBillDetailExample example);

    List<DispatchBillDetail> selectByExampleWithLimit(@Param("example") DispatchBillDetailExample example, @Param("limit") Integer limit);

    List<DispatchBillDetail> selectByExampleForUpdate(DispatchBillDetailExample example);

    List<DispatchBillDetail> selectByExample(DispatchBillDetailExample example);

    DispatchBillDetail selectByPrimaryKeyForUpdate(Long id);

    DispatchBillDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DispatchBillDetail record, @Param("example") DispatchBillDetailExample example);

    int updateByExample(@Param("record") DispatchBillDetail record, @Param("example") DispatchBillDetailExample example);

    int updateByPrimaryKeySelective(DispatchBillDetail record);

    int updateByPrimaryKey(DispatchBillDetail record);
}