
package com.omen.learning.sample.pipeline;


/**
 * @author : suxing.zhang
 * @since : 2020/1/16, Thu
 **/
public class DispatchPipeline extends Pipeline {
    public void flows(DispatchContext context) {
        doFlowing(context);
    }
}
