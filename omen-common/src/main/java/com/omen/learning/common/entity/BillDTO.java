package com.omen.learning.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * @author : Knight
 * @date : 2020/11/8 3:19 下午
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {
    private String orderNo;
    private Long id;
    private String billType;

    public static List<BillDTO> buildParam() {
        return Arrays.asList(new BillDTO(null, 1L, ""), new BillDTO(null, 2L, ""), new BillDTO("jere3", 3L, ""));
    }

    public static BillDTO build(){
        return BillDTO.builder()
                .billType("JIT")
                .id(1000L)
                .orderNo("JEREMY-KNIGHT")
                .build();
    }

    public static BillDTO buildPro(String billType){
        return BillDTO.builder()
                .billType(billType)
                .id(1000L)
                .orderNo("JEREMY-KNIGHT")
                .build();
    }
}

