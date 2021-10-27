package com.omen.learning.sample.support;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author : Knight
 * @date : 2021/10/27 5:11 下午
 */
@Component
public class LuaAdapter implements InitializingBean {


    @Override
    public void afterPropertiesSet()  {
        ArrayList<String> strings = Lists.newArrayList("", "", "");


    }
}
