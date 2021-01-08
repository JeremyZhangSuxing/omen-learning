package com.omen.learning.sample.mybatis.mapper;

import com.omen.learning.sample.mybatis.example.CmOrderExample;
import com.omen.learning.sample.mybatis.po.CmOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CmOrderMapper {
    long countByExample(CmOrderExample example);

    int deleteByExample(CmOrderExample example);

    int insert(CmOrder record);

    int insertSelective(CmOrder record);

    CmOrder selectOneByExample(CmOrderExample example);

    CmOrder selectOneByExampleForUpdate(CmOrderExample example);

    List<CmOrder> selectByExampleForUpdate(CmOrderExample example);

    List<CmOrder> selectByExampleWithLimit(@Param("example") CmOrderExample example, @Param("limit") Integer limit);

    List<CmOrder> selectByExample(CmOrderExample example);

    int updateByExampleSelective(@Param("record") CmOrder record, @Param("example") CmOrderExample example);

    int updateByExample(@Param("record") CmOrder record, @Param("example") CmOrderExample example);
}