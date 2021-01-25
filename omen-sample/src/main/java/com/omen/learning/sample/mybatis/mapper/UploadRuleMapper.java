package com.omen.learning.sample.mybatis.mapper;

import com.omen.learning.sample.mybatis.example.UploadRuleExample;
import com.omen.learning.sample.mybatis.po.UploadRule;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UploadRuleMapper {
    long countByExample(UploadRuleExample example);

    int deleteByExample(UploadRuleExample example);

    int insert(UploadRule record);

    int insertSelective(UploadRule record);

    UploadRule selectOneByExample(UploadRuleExample example);

    UploadRule selectOneByExampleForUpdate(UploadRuleExample example);

    List<UploadRule> selectByExampleForUpdate(UploadRuleExample example);

    List<UploadRule> selectByExampleWithLimit(@Param("example") UploadRuleExample example, @Param("limit") Integer limit);

    List<UploadRule> selectByExample(UploadRuleExample example);

    int updateByExampleSelective(@Param("record") UploadRule record, @Param("example") UploadRuleExample example);

    int updateByExample(@Param("record") UploadRule record, @Param("example") UploadRuleExample example);
}