package com.omen.learning.common.utils;

import org.junit.jupiter.api.Test;

public class JwtUtilsTest {




    @Test
    public void buildJWT() throws Exception {
        String tom = JwtUtils.buildJWT("tom", 1000, "123");
        System.err.println(tom);
        String substring = tom.substring(6);
        System.err.println(substring);
        JwtUtils.validateJWT(substring, "tom", "123");

    }
}