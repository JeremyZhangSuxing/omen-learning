package com.omen.learning.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        return Arrays.asList(new BillDTO("jere1", 1L), new BillDTO("jere2", 1L), new BillDTO("jere3", null));
    }

    /**
     * merge api  会抛出null exception
     * 两种api比较
     */
    public static void main(String[] args) {
        BillDTO billDTO = null;
        Optional.ofNullable(billDTO)
                .map(i -> "d")
                .ifPresent(billDTO::setOrderNo);

        Optional.ofNullable(billDTO)
                .map(i -> "d")
                .ifPresent(i -> billDTO.setOrderNo(i));

    }
}
