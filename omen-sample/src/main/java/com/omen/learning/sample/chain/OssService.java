package com.omen.learning.sample.chain;

import com.omen.learning.common.enums.UploadSource;
import com.omen.learning.sample.chain.node.OssFileNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

/**
 * @author suxing.zhang
 * @date 2021/1/24 10:57
 **/
@Service
public class OssService {
    @Autowired
    private List<OssFileNode> nodes;

    public String uploadFile(MultipartFile multipartFile, String url) {
        Assert.isTrue(Objects.isNull(multipartFile) && StringUtils.isEmpty(url), "未检测到上传文件大小");
        FileContext fileContext = new FileContext();
        if (Objects.nonNull(multipartFile)) {
            fileContext.setMultipartFile(multipartFile);
            fileContext.setSource(UploadSource.MULTIPART.name());
            return new MultipartStreamChainEntry(nodes, fileContext).doUpload(fileContext);
        } else {
            return "url";
        }
    }

}
