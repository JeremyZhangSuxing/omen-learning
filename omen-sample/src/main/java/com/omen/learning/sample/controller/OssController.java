package com.omen.learning.sample.controller;

import com.weweibuy.framework.common.core.model.dto.CommonDataResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author suxing.zhang
 * @date 2021/1/23 18:33
 **/
@RestController
@RequestMapping("/oss")
public class OssController {

    public CommonDataResponse<String> fileUpload(@RequestParam(required = false) MultipartFile multipartFile,
                                                 @RequestParam(required = false) String url) {


        return CommonDataResponse.success("url");
    }
}
