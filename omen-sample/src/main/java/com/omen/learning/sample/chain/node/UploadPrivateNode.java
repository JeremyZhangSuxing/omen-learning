package com.omen.learning.sample.chain.node;

import com.omen.learning.sample.chain.FileContext;
import org.springframework.stereotype.Component;

/**
 * @author suxing.zhang
 * @date 2021/1/23 12:00
 **/
@Component
public class UploadPrivateNode implements OssFileNode{
    @Override
    public Object doNode(FileContext fileContext) {


        return null;
    }

    @Override
    public boolean match(FileContext fileContext) {
        return false;
    }

    @Override
    public int order() {
        return 0;
    }
}
