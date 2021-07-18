package com.omen.learning.sample.pipeline;

import com.omen.learning.common.entity.Dispatch;
import com.omen.learning.common.entity.DispatchItem;
import lombok.*;

import java.util.List;

/**
 * @author : Knight
 * @date : 2021/7/18 3:13 下午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DispatchContext extends BaseContext {
    private Dispatch dispatch;
    private List<DispatchItem> items;

}
