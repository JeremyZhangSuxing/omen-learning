package com.omen.learning.common;

import com.omen.learning.common.annotation.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @author : Knight
 * @date : 2020/11/8 3:19 下午
 */
@Data
@AllArgsConstructor
public class BillDTO {
    private String orderNo;
    private Long id;

    public static List<BillDTO> buildParam() {
        return Arrays.asList(new BillDTO("jere1", 1L), new BillDTO("jere2", 2L));
    }

    @ServiceException(code = 10)
    public String get() {
        return "jeremy";
    }

}
