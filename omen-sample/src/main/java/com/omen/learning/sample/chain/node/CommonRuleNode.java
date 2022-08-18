package com.omen.learning.sample.chain.node;

import com.omen.learning.sample.chain.FileContext;
import com.omen.learning.sample.mybatis.example.UploadRuleExample;
import com.omen.learning.sample.mybatis.mapper.UploadRuleMapper;
import com.omen.learning.sample.mybatis.po.UploadRule;
import com.weweibuy.framework.common.core.exception.Exceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author suxing.zhang
 * @date 2021/1/23 11:55
 **/
@Component
@RequiredArgsConstructor
public class CommonRuleNode implements OssFileNode {
    private final UploadRuleMapper uploadRuleMapper;

    @Override
    public void doNode(FileContext fileContext) {
        UploadRuleExample uploadRuleExample = new UploadRuleExample();
        uploadRuleExample.createCriteria().andBusinessTypeEqualTo(fileContext.getBusinessType());
        UploadRule uploadRule = Optional.ofNullable(uploadRuleMapper.selectOneByExample(uploadRuleExample)).orElseThrow(
                () -> Exceptions.business("11111", "未配置oss上传规则"));
        fileContext.setUploadRule(uploadRule);
    }

    @Override
    public boolean match(FileContext fileContext) {
        return true;
    }

    @Override
    public int order() {
        return 0;
    }
}
