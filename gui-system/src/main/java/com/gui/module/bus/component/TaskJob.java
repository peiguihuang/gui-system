package com.gui.module.bus.component;


import com.gui.module.bus.dao.OrderStatisticsDao;
import com.gui.module.bus.dao.SubOrderDao;
import com.gui.module.bus.domain.OrderStatisticsDO;
import com.gui.module.bus.domain.SubOrderDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @Author ：peigui.huang
 * @Date ：2020/5/25
 * @Description ：
 */
@Slf4j
@Component
public class TaskJob {

    @Autowired
    private OrderStatisticsDao orderStatisticsDao;

    @Autowired
    private SubOrderDao subOrderDao;

    @Scheduled(cron = "0 0 1 * * ?")
    public void scheduled2() {
        log.info("开始执行定时任务");

        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.DATE, -1);
        Date startTime = c.getTime();
        Map<String,Object> params = new HashMap<>();
        String startTimeStr = new SimpleDateFormat("yyyy-MM-dd").format(startTime);
        params.put("startTime",startTimeStr);
        params.put("endTime",new SimpleDateFormat("yyyy-MM-dd").format(now));

        List<SubOrderDO> subOrderDOS = subOrderDao.list(params);

        BigDecimal totalAmount = new BigDecimal("0.00");
        Integer cancelCount = 0;
        Integer payCount = 0;
        Integer waitPayCount = 0;
        if (!CollectionUtils.isEmpty(subOrderDOS)){
            for (int i=0;i<subOrderDOS.size();i++){
                SubOrderDO subOrderDO = subOrderDOS.get(i);
                if (subOrderDO.getPayStatus() == 0){
                    waitPayCount++;
                }else if (subOrderDO.getPayStatus() == 1){
                    payCount++;
                    totalAmount = totalAmount.add(subOrderDO.getAmount());

                }else {
                    cancelCount++;
                }
            }
        }
        OrderStatisticsDO orderStatisticsDO = new OrderStatisticsDO();
        orderStatisticsDO.setStatisticsDay(startTimeStr);
        orderStatisticsDO.setCancelCount(cancelCount);
        orderStatisticsDO.setPayCount(payCount);
        orderStatisticsDO.setTotalAmount(totalAmount);
        orderStatisticsDO.setWaitPayCount(waitPayCount);
        orderStatisticsDO.setCreateTime(new Date());
        orderStatisticsDO.setUpdateTime(new Date());
        orderStatisticsDao.save(orderStatisticsDO);
        log.info("结束执行定时任务");

    }

}
