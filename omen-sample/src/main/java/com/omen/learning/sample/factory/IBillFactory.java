
package com.omen.learning.sample.factory;


import com.omen.learning.sample.pipeline.Pipeline;

/**
 * @author : suxing.zhang
 * @since : 2020/1/17, Fri
 **/
public interface IBillFactory {
    /**
     * pipeline 入口
     * @param param pipeline 入口
     * @return 构建pipeline
     */
    Pipeline build(Object param);

    /**
     * 适配规则
     * @param condition PipeLineFactory 适配规则
     * @return 适配结果
     */
    boolean matchByCondition(BeanMatchCondition condition);
}
