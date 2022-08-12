package com.omen.learning.sample.service;

import com.omen.learning.common.entity.BillDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Order;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author : Knight
 * @date : 2021/4/23 5:38 下午
 */
@Slf4j
@Service
public class EventService {

    /**
     * 处理jIT发货单
     * 一般事件发布为单线程处理，可以用以代码重构时不改变原代码结构挥着避免循环依赖等case
     * 可以通过 {@link org.junit.jupiter.api.Order} 指定同一事件消费次序
     */
    @Async
    @EventListener(condition = "#billDTO.billType.equalsIgnoreCase('JITX')")
    public void onListenDeliveryOrder(BillDTO billDTO) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5L);
        HttpServletRequest httpServletRequest = billDTO.getHttpServletRequest();
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            log.info("key-------> {} value-----> {}", key, httpServletRequest.getHeader(key));
        }
    }

    @EventListener(condition = "#billDTO.billType.equalsIgnoreCase('JIT')")
    @Order(1)
    public void onListenDeliveryOrder2(BillDTO billDTO) throws InterruptedException {
        Thread.sleep(5000);
        log.info("start to delivery JIT dispatch bill ....." + LocalDateTime.now());
    }

    @EventListener(condition = "#billDTO.billType.equalsIgnoreCase('JIT')")
    @Order(2)
    public void onListenDeliveryOrder3(BillDTO billDTO) throws InterruptedException {
        Thread.sleep(5000);
        log.info("start to delivery JIT dispatch bill ....." + LocalDateTime.now());
    }

    /**
     * 处理jit发货单回执
     */
    @Async
    @EventListener(condition = "#billDTO.billType.equalsIgnoreCase('JIT')")
    public void onListenDeliveryReceipt(BillDTO billDTO) {
        log.info("start to validate JIT receipt  bill ....." + LocalDateTime.now());
    }

    /**
     * 处理B2C发货单
     * 事务隔离的时间发布 发布时间的地方事务执行成功，这个事件才会被执行，如果简监听的地方出现异常，发布事件的地方无法感知到
     * fallbackExecution 可以指定如果发布事件的地方没有指定 事务 则此事件不会被成功消费
     */
    @TransactionalEventListener(condition = "#billDTO.billType.equalsIgnoreCase('B2C')", phase = TransactionPhase.AFTER_COMMIT, fallbackExecution = true)
    public void onListenB2cDeliveryOrder(BillDTO billDTO) {
        log.info("start to deal with b2c delivery order ,but something error...");
    }
}
