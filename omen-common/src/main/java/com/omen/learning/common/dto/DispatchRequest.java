package com.omen.learning.common.dto;

import com.omen.learning.common.entity.Dispatch;
import com.omen.learning.common.entity.DispatchItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

/**
 * @author suxing.zhang
 * @date 2021/7/18 15:39
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DispatchRequest {

    /**
     * 单据信息
     */
    @Valid
    private Dispatch dispatch;

    /**
     * 明细
     */
    @Valid
    private List<DispatchItem> items;

}
