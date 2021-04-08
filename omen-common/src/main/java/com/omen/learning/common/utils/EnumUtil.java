package com.omen.learning.common.utils;

import com.omen.learning.common.enums.CodeEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnumUtil {

    public static <T extends CodeEnum> String getMsgByCode(Integer code, Class<T> t) {
        for (T item : t.getEnumConstants()) {
            if (item.getCode() == code) {
                return item.getMsg();
            }
        }
        return "";
    }

    public static <T extends CodeEnum> Integer getCodeByMsg(String msg, Class<T> t) {
        for (T item : t.getEnumConstants()) {
            if (StringUtils.equals(item.getMsg(), msg)) {
                return item.getCode();
            }
        }
        return 500;
    }

}