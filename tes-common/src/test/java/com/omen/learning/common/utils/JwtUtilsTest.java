package com.omen.learning.common.utils;

import org.junit.Test;

public class JwtUtilsTest {




    @Test
    public void buildJWT() throws Exception {
        String tom = JwtUtils.buildJWT("tom", 1000, "123");
        System.err.println(tom);

        String substring = tom.substring(6, tom.length());
        System.err.println(substring);
        JwtUtils.validateJWT(substring, "tom", "123");

    }
}