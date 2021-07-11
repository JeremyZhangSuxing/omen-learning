
package com.omen.learning.sample.factory;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author :
 * @since : 2020/1/19, Sun
 **/
@Data
@Builder
public class BeanMatchCondition {
    /**
     * 业务类型
     */
    private String bizType;
    /**
     * 任务类型
     */
    private String taskType;
    /**
     * bean类型，1：common；2：task
     */
    @Builder.Default
    private Integer beanType = NumberUtils.INTEGER_ONE;
}
