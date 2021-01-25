package com.omen.learning.sample.mybatis.po;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UploadRule {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 映射字段
     */
    private String mapping;

    /**
     * 访问权限
     */
    private String authority;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;

    /**
     * 是否删除
     */
    private Boolean isDeleted;
}