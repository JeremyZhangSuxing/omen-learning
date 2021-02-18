package com.omen.learning.sample.test;

import com.omen.learning.sample.mybatis.po.CmOrder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@Slf4j
@DisplayName("订单功能测试")
@SpringBootTest
class TestServiceTest {
    @Autowired
    private TestService testService;

    @Test
    @DisplayName(" 测试订单列表")
    void listOrders() {
        CmOrder cmOrder = testService.listOrders(1L);
        Assert.assertEquals(1, cmOrder.getId().longValue());
    }

    @Test
    @DisplayName("测试订单列表 anotherOne")
    void listOrdersAnotherOne() {
        CmOrder cmOrder = testService.listOrders(1L);
        Assert.assertEquals(1, cmOrder.getId().longValue());
    }

    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    void testTimeOut() throws InterruptedException {
        Thread.sleep(3000L);
    }

    @RepeatedTest(value = 5)
    void testRepeat() {
        log.info("---------execute repeat--------");
    }

    @Test
    @DisplayName("组合断言")
    void testPredicate() {
        int a = 10;
        Assertions.assertAll(
                "testPredicate start....",
                () -> Assertions.assertEquals(10, a),
                () -> Assertions.assertEquals("jeremy", "jeremy")
        );
    }

    @Test
    void testAssumption() {
        Assumptions.assumeFalse(true, "前置校验未通过，方法不执行");
    }

    @BeforeAll
    static void beforeAll() {
        log.info("---------execute before all function--------");
    }

    @AfterAll
    static void afterAll() {
        log.info("---------execute after all function--------");
    }

    @BeforeEach
    void beforeEach() {
        log.info("---------execute before each function--------");
    }

    @AfterEach
    void afterEach() {
        log.info("---------execute after each function--------");
    }
}