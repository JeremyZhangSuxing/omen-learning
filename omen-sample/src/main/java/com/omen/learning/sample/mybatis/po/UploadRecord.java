package com.omen.learning.sample.mybatis.po;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UploadRecord {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * oss地址
     */
    private String ossUrl;

    /**
     * 本地地址
     */
    private String localUrl;

    /**
     * 文件原始名称
     */
    private String fileName;

    /**
     * 原始url
     */
    private String originalUrl;

    /**
     * 上传源
     */
    private String source;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 更新人
     */
    private String updatedBy;
}