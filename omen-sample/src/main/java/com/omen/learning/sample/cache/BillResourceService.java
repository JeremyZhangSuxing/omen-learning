package com.omen.learning.sample.cache;

import com.omen.learning.common.entity.BillDTO;
import com.omen.learning.sample.client.OrderFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author jeremy.zhang
 * @date 2022-08-12
 */
@Component
@RequiredArgsConstructor
public class BillResourceService {
    private final OrderFeignClient orderFeignClient;

    public Map<String,BillDTO> loadBillResource() {
        Map<String, BillDTO> readCache = LocalBillCache.readCache();
        if (Objects.nonNull(readCache)) {
            return readCache;
        }
        List<BillDTO> billDTOS = orderFeignClient.fetchBills();
        LocalBillCache.writeCache(billDTOS);
        return LocalBillCache.readCache();
    }
}
