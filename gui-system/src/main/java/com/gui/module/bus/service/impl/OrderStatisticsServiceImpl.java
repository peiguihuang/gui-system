package com.gui.module.bus.service.impl;

import com.gui.module.bus.dao.SubOrderDao;
import com.gui.module.bus.domain.SubOrderDO;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import com.gui.module.bus.dao.OrderStatisticsDao;
import com.gui.module.bus.domain.OrderStatisticsDO;
import com.gui.module.bus.service.OrderStatisticsService;


@Service
public class OrderStatisticsServiceImpl implements OrderStatisticsService {
    @Autowired
    private OrderStatisticsDao orderStatisticsDao;

    @Override
    public OrderStatisticsDO get(Long id) {
        return orderStatisticsDao.get(id);
    }

    @Autowired
    private SubOrderDao subOrderDao;

    @Override
    public List<OrderStatisticsDO> list(Map<String, Object> map) {
        return orderStatisticsDao.list(map);
    }

    @Override
    public OrderStatisticsDO todayStatistics() {
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.DATE, 1);
        Date endTime = c.getTime();
        Map<String, Object> params = new HashMap<>();
        String endTimeStr = new SimpleDateFormat("yyyy-MM-dd").format(endTime);
        params.put("startTime", new SimpleDateFormat("yyyy-MM-dd").format(now));
        params.put("endTime", endTimeStr);

        List<SubOrderDO> subOrderDOS = subOrderDao.listStatistics(params);

        BigDecimal totalAmount = new BigDecimal("0.00");
        Integer cancelCount = 0;
        Integer payCount = 0;
        Integer waitPayCount = 0;
        if (!CollectionUtils.isEmpty(subOrderDOS)) {
            for (int i = 0; i < subOrderDOS.size(); i++) {
                SubOrderDO subOrderDO = subOrderDOS.get(i);
                if (subOrderDO.getPayStatus() == 0) {
                    waitPayCount++;
                } else if (subOrderDO.getPayStatus() == 1) {
                    payCount++;
                    totalAmount = totalAmount.add(subOrderDO.getAmount());

                } else {
                    cancelCount++;
                }
            }
        }
        OrderStatisticsDO orderStatisticsDO = new OrderStatisticsDO();
        orderStatisticsDO.setCancelCount(cancelCount);
        orderStatisticsDO.setPayCount(payCount);
        orderStatisticsDO.setTotalAmount(totalAmount);
        orderStatisticsDO.setWaitPayCount(waitPayCount);
        orderStatisticsDO.setCreateTime(new Date());
        orderStatisticsDO.setUpdateTime(new Date());
        return orderStatisticsDO;
    }

    @Override
    public int count(Map<String, Object> map) {
        return orderStatisticsDao.count(map);
    }

    @Override
    public int save(OrderStatisticsDO orderStatistics) {
        return orderStatisticsDao.save(orderStatistics);
    }

    @Override
    public int update(OrderStatisticsDO orderStatistics) {
        return orderStatisticsDao.update(orderStatistics);
    }

    @Override
    public int remove(Long id) {
        return orderStatisticsDao.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return orderStatisticsDao.batchRemove(ids);
    }

}
