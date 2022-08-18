package com.omen.learning.sample.pipeline;

import com.weweibuy.framework.common.core.model.dto.CommonDataResponse;
import lombok.Data;

/**
 * @author suxing.zhang
 * @date 2021/7/18 15:39
 **/
@Data
public abstract class BaseContext<T> {
    /**
     * 唯一请求id
     */
    protected String reqId;
    /**
     * 中断标志
     */
    protected boolean interruptSignal = false;
    /**
     * 请求响应
     */
    protected CommonDataResponse<T> resp;

}