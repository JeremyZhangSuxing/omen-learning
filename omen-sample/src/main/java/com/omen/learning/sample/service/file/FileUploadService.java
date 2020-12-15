package com.omen.learning.sample.service.file;

import com.weweibuy.framework.common.core.exception.BusinessException;
import com.weweibuy.framework.common.core.model.dto.CommonCodeResponse;
import com.weweibuy.framework.common.core.utils.IdWorker;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author : Knight
 * @date : 2020/12/11 2:42 下午
 */
@Service
public abstract class FileUploadService {

    public String upload(MultipartFile multipartFile) throws IOException {
        return generateFileName(multipartFile.getOriginalFilename());
//        String contentType = getContentType(originalFilename);
    }


    /**
     * 获取规范的 mediaType
     *
     * @param originalFileName 文件原名称
     */
    protected String getContentType(String originalFileName) {
        String fileType = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        switch (fileType) {
            case "pdf":
                return MediaType.APPLICATION_PDF_VALUE;
            case "jpeg":
            case "jpg":
                return MediaType.IMAGE_JPEG_VALUE;
            case "png":
                return MediaType.IMAGE_PNG_VALUE;
            default:
                throw new BusinessException(CommonCodeResponse.badRequestParam(""));
        }
    }

    /**
     * 生成全局唯一的文件名
     * 不允许采用uuid
     */
    protected static String generateFileName(String originalFileName) {
        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
        return IdWorker.nextId() + "-" + System.currentTimeMillis() + suffix;
    }
}
