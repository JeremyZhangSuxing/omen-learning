package com.omen.learning.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author : Knight
 * @date : 2020/11/8 3:19 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {
    private String orderNo;
    private Long id;

    public static List<BillDTO> buildParam() {
        return Arrays.asList(new BillDTO(null, 1L), new BillDTO(null, 2L), new BillDTO("jere3", 3L));
    }

    /**
     * merge api  会抛出null exception
     * 两种api比较
     */
    public static void main(String[] args) {

        List<BillDTO> list = new ArrayList<>();
        Map<String, BillDTO> map = list.stream().collect(Collectors.toMap(key -> key.getOrderNo(), Function.identity()));
        System.out.println(map);
        System.out.println(map.get(""));
        List<BillDTO> dtoList = buildParam();
        dtoList.stream().collect(Collectors.toMap(key -> key.getId(), value -> value.getOrderNo(), (o, n) -> n));
//        Map<String,BillDTO> newMap = new HashMap<>();
//        for (BillDTO billDTO : dtoList) {
//            newMap.put(billDTO.getOrderNo(),map.get(billDTO.getOrderNo()));
//        }
        System.out.println("11111111111111");
        Map<Long, BillDTO> collect = dtoList.stream().collect(Collectors.toMap(key -> key.getId(), value -> map.get(value.getOrderNo())));

//        BillDTO billDTO = null;
//        Optional.ofNullable(billDTO)
//                .map(i -> "d")
//                .ifPresent(billDTO::setOrderNo);
//
//        Optional.ofNullable(billDTO)
//                .map(i -> "d")
//                .ifPresent(i -> billDTO.setOrderNo(i));
//

    }
}
