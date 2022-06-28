
package com.omen.learning.sample.valve.validator;


import com.omen.learning.sample.valve.Valve;

/**
 * @author : knight
 * @since : 2020/1/16, Thu
 **/
public interface Validator extends Valve {
    /**
     * @param param
     */
    void validate(Object param);

    @Override
    default void invoke(Object param) {
        validate(param);
    }
}
