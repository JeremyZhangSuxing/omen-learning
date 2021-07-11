
package com.omen.learning.sample.factory;


import com.omen.learning.sample.pipeline.Pipeline;

/**
 * @author : suxing.zhang
 * @since : 2020/1/17, Fri
 **/
public interface IBillFactory {
    /**
     * @param param
     * @return
     */
    Pipeline build(Object param);

    /**
     * @param condition
     * @return
     */
    boolean matchByCondition(BeanMatchCondition condition);
}
