package com.omen.learning.sample.chain.node;

import com.omen.learning.sample.chain.FileContext;

/**
 * @author suxing.zhang
 * @date 2021/1/23 10:55
 **/
public interface OssFileNode {
    void doNode(FileContext fileContext);

    boolean match(FileContext fileContext);

    int order();
}
