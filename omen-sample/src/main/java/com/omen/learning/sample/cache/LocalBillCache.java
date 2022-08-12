package com.omen.learning.sample.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.omen.learning.common.entity.BillDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author jeremy.zhang
 * @date 2022-08-12
 */
public class LocalBillCache {

    private static final Cache<String, Map<String, BillDTO>> BILL_CACHE = CacheBuilder.newBuilder()
            .initialCapacity(16)
            .maximumSize(16)
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .build();

    private static final String BILL_CACHE_KEY = "bill_cache";


    public static void writeCache(List<BillDTO> list) {
        if (Objects.nonNull(BILL_CACHE.getIfPresent(BILL_CACHE_KEY))) {
            return;
        }
        Map<String, BillDTO> map = new HashMap<>(256);
        for (BillDTO billDTO : list) {
            map.put(billDTO.getOrderNo(), billDTO);
        }
        BILL_CACHE.put(BILL_CACHE_KEY, map);
    }

    public static Map<String, BillDTO> readCache() {
        return BILL_CACHE.getIfPresent(BILL_CACHE_KEY);
    }
}
