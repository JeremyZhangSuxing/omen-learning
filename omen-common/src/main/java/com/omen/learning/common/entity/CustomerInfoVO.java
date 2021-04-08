package com.omen.learning.common.entity;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author : Knight
 * @date : 2020/11/30 10:29 上午
 */
@Data
public class CustomerInfoVO {
    private String locationName;

    private String skuName;

    private String skuUuid;


    public static List<CustomerInfoVO> buildList(){
        CustomerInfoVO customerInfoVO = new CustomerInfoVO();
        customerInfoVO.setLocationName("中海海");
        customerInfoVO.setSkuName("办公室");
        customerInfoVO.setSkuUuid("001");
        CustomerInfoVO customerInfoVO2 = new CustomerInfoVO();
        BeanUtils.copyProperties(customerInfoVO,customerInfoVO2);
        customerInfoVO2.setLocationName("世界之窗");
        customerInfoVO2.setSkuName("hot desk");
        return Arrays.asList(customerInfoVO,customerInfoVO2);
    }
}
