package com.omen.learning.sample.chain.node;

import com.omen.learning.common.enums.UploadSource;
import com.omen.learning.sample.chain.FileContext;
import org.springframework.stereotype.Component;

/**
 * @author suxing.zhang
 * @date 2021/1/23 12:04
 **/
@Component
public class UrlFileNameNode implements OssFileNode {
    @Override
    public void doNode(FileContext fileContext) {
    }

    @Override
    public boolean match(FileContext fileContext) {
        return UploadSource.URL.name().equals(fileContext.getSource());
    }

    @Override
    public int order() {
        return 0;
    }
}
