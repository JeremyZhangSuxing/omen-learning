package com.omen.learning.sample.chain;

import com.omen.learning.sample.chain.node.OssFileNode;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author suxing.zhang
 * @date 2021/1/23 10:50
 **/
public class MultipartStreamChainEntry implements FileUploadChain {
    private List<OssFileNode> ossFileNodes;


    public MultipartStreamChainEntry(List<OssFileNode> ossFileNodes,FileContext fileContext) {
        this.ossFileNodes = ossFileNodes.stream()
                .filter(v->Boolean.TRUE.equals(v.match(fileContext)))
                .sorted(Comparator.comparingInt(OssFileNode::order))
                .collect(Collectors.toList());
    }


    @Override
    public String doUpload(FileContext fileContext) {
        for (OssFileNode ossFileNode : ossFileNodes) {
            ossFileNode.doNode(fileContext);
        }
        return chainExit(fileContext);
    }

    /**
     * 公网权限返回ossUrl
     * 公司内部权限转化为公司域名映射的url
     */
    @Override
    public String chainExit(FileContext fileContext){
        return fileContext.getUploadRecord().getOssUrl();
    }
}
