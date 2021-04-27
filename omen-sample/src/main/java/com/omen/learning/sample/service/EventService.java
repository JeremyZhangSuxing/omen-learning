package com.omen.learning.sample.service;

import com.omen.learning.common.entity.BillDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.LocalDateTime;

/**
 * @author : Knight
 * @date : 2021/4/23 5:38 下午
 */
@Slf4j
@Service
public class EventService {

    /**
     * 处理jIT发货单
     */
    @Async
    @EventListener(condition = "#billDTO.billType.equalsIgnoreCase('JIT')")
    public void onListenDeliveryOrder(BillDTO billDTO) throws InterruptedException {
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
     * 事务消息，
     */
    @TransactionalEventListener(condition = "#billDTO.billType.equalsIgnoreCase('B2C')",phase = TransactionPhase.AFTER_COMMIT)
    public void onListenB2cDeliveryOrder(BillDTO billDTO) {
        log.info("start to deal with b2c delivery order ,but something error...");
    }
}
