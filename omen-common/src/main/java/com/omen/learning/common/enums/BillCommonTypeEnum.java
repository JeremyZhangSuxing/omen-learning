package com.omen.learning.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
/**
 * @author suxing.zhang
 * @date 2021/7/18 15:21
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum BillCommonTypeEnum {
    DISPATCH("D"),
    RECEIVE("R"),
    DEFAULT("DEFAULT"),
    ;


    private static final Map<String, BillCommonTypeEnum> billCommonTypeEnumMap = new HashMap<>();

    static {
        for (BillCommonTypeEnum billCommonTypeEnum : BillCommonTypeEnum.values()) {
            billCommonTypeEnumMap.put(billCommonTypeEnum.getPrefix(), billCommonTypeEnum);
        }

    }

    private String prefix;

    public static BillCommonTypeEnum findByPreFix(String prefix) {
        return billCommonTypeEnumMap.get(prefix);
    }


}