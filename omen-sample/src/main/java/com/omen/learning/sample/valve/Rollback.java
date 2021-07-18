
package com.omen.learning.sample.valve;

/**
 * @author : suxing.zhang
 * @since : 2020/2/10, Mon
 **/
public interface Rollback {
    void rollback(Object param, Throwable t);
}
