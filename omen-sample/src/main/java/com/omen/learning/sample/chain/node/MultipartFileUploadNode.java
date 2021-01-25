package com.omen.learning.sample.chain.node;

import com.omen.learning.common.enums.UploadSource;
import com.omen.learning.sample.chain.FileContext;
import com.omen.learning.sample.mybatis.po.UploadRecord;
import org.springframework.stereotype.Component;

/**
 * @author suxing.zhang
 * @date 2021/1/23 12:09
 **/
@Component
public class MultipartFileUploadNode implements OssFileNode{
    @Override
    public boolean match(FileContext fileContext) {
        return UploadSource.MULTIPART.name().equals(fileContext.getSource());
    }

    @Override
    public Object doNode(FileContext fileContext) {
        //上传完成后会记录此文件的地址
        String urlStr = "https://img.alicdn.com/bao/uploaded/i3/83436916/O1CN01vO4FrV20xZl3MrqXv_!!83436916.jpg_210x210Q50s50.jpg_.webp";
        UploadRecord record = fileContext.getUploadRecord();
        record.setOssUrl(urlStr);
        return fileContext;
    }

    @Override
    public int order() {
        return 3;
    }
}
