
package com.omen.learning.sample.valve;

/**
 * @author :
 * @since : 2020/2/10, Mon
 **/
public interface Rollback {
    /**
     * @param param
     * @param t
     */
    void rollback(Object param, Throwable t);
}
