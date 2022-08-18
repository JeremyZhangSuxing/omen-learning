package com.omen.learning.sample.chain.node;

import com.omen.learning.sample.chain.FileContext;
import com.omen.learning.sample.mybatis.mapper.UploadRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author suxing.zhang
 * @date 2021/1/23 11:57
 **/
@Component
@RequiredArgsConstructor
public class UploadRecordSaveNode implements OssFileNode{
    private final UploadRecordMapper uploadRecordMapper;
    @Override
    public void doNode(FileContext fileContext) {
        uploadRecordMapper.insertSelective(fileContext.getUploadRecord());
    }

    @Override
    public boolean match(FileContext fileContext) {
        return true;
    }

    @Override
    public int order() {
        return Integer.MAX_VALUE;
    }
}
