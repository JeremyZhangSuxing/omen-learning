package com.omen.learning.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

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
    private Enumeration<String> headerNames;
    private HttpServletRequest httpServletRequest;


    public static BillDTO build(){
        return BillDTO.builder()
                .billType("JITX")
                .id(1000L)
                .orderNo("JEREMY-KNIGHT")
                .build();
    }

    public static BillDTO buildPro(String billType, Enumeration<String> headerNames){
        return BillDTO.builder()
                .billType(billType)
                .id(1000L)
                .orderNo("JEREMY-KNIGHT")
                .headerNames(headerNames)
                .build();
    }

    public static BillDTO buildWithReq(String billType, HttpServletRequest httpServletRequest){
        return BillDTO.builder()
                .billType(billType)
                .id(1000L)
                .orderNo("JEREMY-KNIGHT")
                .httpServletRequest(httpServletRequest)
                .build();
    }
}

