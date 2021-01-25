package com.omen.learning.sample.chain;

import com.omen.learning.sample.mybatis.po.UploadRecord;
import com.omen.learning.sample.mybatis.po.UploadRule;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author suxing.zhang
 * @date 2021/1/23 10:41
 **/
@Data
public class FileContext {
    private MultipartFile multipartFile;
    private String url;
    private String source;
    private String contentType;
    private UploadRecord uploadRecord;
    private UploadRule uploadRule;
    private String businessType;
    private String ossFileName;

}
