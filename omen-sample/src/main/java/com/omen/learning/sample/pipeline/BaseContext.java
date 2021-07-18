package com.omen.learning.sample.pipeline;

import lombok.Data;

@Data
public abstract class BaseContext {
    /**
     * 唯一请求id
     */
    protected String reqId;
    /**
     * 中断标志
     */
    protected boolean interruptSignal = false;
}