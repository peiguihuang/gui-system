package com.gui.module.bus.service;

import com.gui.module.bus.domain.OrderStatisticsDO;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author peigui.huang
 * @email 1157688065@qq.com
 * @date 2020-05-30 21:59:36
 */
public interface OrderStatisticsService {

        OrderStatisticsDO get(Long id);

    OrderStatisticsDO todayStatistics();

    List<OrderStatisticsDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(OrderStatisticsDO orderStatistics);

    int update(OrderStatisticsDO orderStatistics);

    int remove(Long id);

    int batchRemove(Long[] ids);
}
