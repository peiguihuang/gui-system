package com.gui.module.bus.service;

import com.gui.module.bus.domain.SubOrderDO;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author peigui.huang
 * @email 1157688065@qq.com
 * @date 2020-05-30 17:45:33
 */
public interface SubOrderService {

        SubOrderDO get(Long id);

    List<SubOrderDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(SubOrderDO subOrder);

    int update(SubOrderDO subOrder);

    int remove(Long id);

    int batchRemove(Long[] ids);
}
