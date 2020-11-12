package com.omen.learning.sample.concurrent;

import com.omen.learning.common.BillDTO;
import com.omen.learning.common.annotation.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author : Knight
 * @date : 2020/11/8 3:16 下午
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PoolTestService {
    private final ExecutorService commonInvokeExecutor;

    @ServiceException(code = 10)
    public List<String> batchGetBillInfo(List<BillDTO> billDTOS) {
        List<Future<String>> collect = billDTOS.stream()
                .map(v -> commonInvokeExecutor.submit(() -> convertBillInfo(v)))
                .collect(Collectors.toList());
        List<String> list = new ArrayList<>();
        for (Future<String> stringFuture : collect) {
            try {
                list.add(stringFuture.get(3, TimeUnit.SECONDS));
            } catch (Exception e) {
                log.error("e:" + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    private String convertBillInfo(BillDTO billDTO) {
        return Thread.currentThread().getName()+billDTO.getOrderNo() + billDTO.getId();
    }
}
