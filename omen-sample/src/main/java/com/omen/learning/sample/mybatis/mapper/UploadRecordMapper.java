package com.omen.learning.sample.mybatis.mapper;

import com.omen.learning.sample.mybatis.example.UploadRecordExample;
import com.omen.learning.sample.mybatis.po.UploadRecord;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UploadRecordMapper {
    long countByExample(UploadRecordExample example);

    int deleteByExample(UploadRecordExample example);

    int insert(UploadRecord record);

    int insertSelective(UploadRecord record);

    UploadRecord selectOneByExample(UploadRecordExample example);

    UploadRecord selectOneByExampleForUpdate(UploadRecordExample example);

    List<UploadRecord> selectByExampleForUpdate(UploadRecordExample example);

    List<UploadRecord> selectByExampleWithLimit(@Param("example") UploadRecordExample example, @Param("limit") Integer limit);

    List<UploadRecord> selectByExample(UploadRecordExample example);

    int updateByExampleSelective(@Param("record") UploadRecord record, @Param("example") UploadRecordExample example);

    int updateByExample(@Param("record") UploadRecord record, @Param("example") UploadRecordExample example);
}