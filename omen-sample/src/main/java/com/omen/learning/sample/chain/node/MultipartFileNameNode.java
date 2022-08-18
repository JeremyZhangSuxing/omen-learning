package com.omen.learning.sample.chain.node;

import com.omen.learning.common.enums.UploadSource;
import com.omen.learning.sample.chain.FileContext;
import com.omen.learning.sample.mybatis.po.UploadRecord;
import com.weweibuy.framework.common.core.utils.IdWorker;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author suxing.zhang
 * @date 2021/1/23 10:59
 **/
@Component
public class MultipartFileNameNode implements OssFileNode {


    @Override
    public void doNode(FileContext fileContext) {
        UploadRecord record = new UploadRecord();
        String originalFilename = fileContext.getMultipartFile().getOriginalFilename();
        record.setFileName(originalFilename);
        String fileName = IdWorker.nextStringId() + Objects.requireNonNull(originalFilename)
                .substring(originalFilename.lastIndexOf("."));
        fileContext.setOssFileName(fileName);
        record.setCreatedBy("jeremy");
        record.setCreatedTime(LocalDateTime.now());
        record.setUpdatedBy("jeremy");
        record.setUpdatedTime(LocalDateTime.now());
        record.setBusinessType(fileContext.getBusinessType());
        //todo  处理 context
        fileContext.setContentType("");
    }

    @Override
    public boolean match(FileContext fileContext) {
        return UploadSource.MULTIPART.name().equals(fileContext.getSource());
    }

    @Override
    public int order() {
        return 1;
    }
}
