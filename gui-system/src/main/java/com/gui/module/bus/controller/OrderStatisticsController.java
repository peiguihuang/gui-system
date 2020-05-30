package com.gui.module.bus.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gui.base.BasePageResponse;
import com.gui.base.BaseResponse;

import com.gui.module.bus.domain.OrderStatisticsDO;
import com.gui.module.bus.service.OrderStatisticsService;
import com.gui.module.common.utils.Query;
import org.springframework.stereotype.Controller;
import com.gui.utils.ResponseUtils;

/**
 * 
 *
 * @author peigui.huang
 * @email 1157688065@qq.com
 * @date 2020-05-30 21:59:36
 */

@Controller
@RequestMapping("/bus/orderStatistics")
public class OrderStatisticsController {
    @Autowired
    private OrderStatisticsService orderStatisticsService;

    @GetMapping()
    @RequiresPermissions("bus:orderStatistics:orderStatistics")
    String OrderStatistics() {
        return "bus/orderStatistics/orderStatistics";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("bus:orderStatistics:orderStatistics")
    public BasePageResponse list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<OrderStatisticsDO> orderStatisticsList = orderStatisticsService.list(query);
        int total = orderStatisticsService.count(query);
        return ResponseUtils.buildPageSuccess(total, orderStatisticsList);
    }

    @GetMapping("/add")
    @RequiresPermissions("bus:orderStatistics:add")
    String add() {
        return "bus/orderStatistics/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("bus:orderStatistics:edit")
    String edit(@PathVariable("id") Long id, Model model) {
            OrderStatisticsDO orderStatistics = orderStatisticsService.get(id);
        model.addAttribute("orderStatistics", orderStatistics);
        return "bus/orderStatistics/edit";
    }

    @ResponseBody
    @GetMapping("/query")
    @RequiresPermissions("bus:orderStatistics:orderStatistics")
    OrderStatisticsDO orderStatistics() {
        OrderStatisticsDO orderStatistics = orderStatisticsService.todayStatistics();
        return orderStatistics;
    }
    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("bus:orderStatistics:add")
    public BaseResponse<Void> save( OrderStatisticsDO orderStatistics) {
        if (orderStatisticsService.save(orderStatistics) > 0) {
            return ResponseUtils.success();
        }
        return ResponseUtils.fail();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("bus:orderStatistics:edit")
    public BaseResponse<Void> update( OrderStatisticsDO orderStatistics) {
            orderStatisticsService.update(orderStatistics);
        return ResponseUtils.success();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("bus:orderStatistics:remove")
    public BaseResponse<Void> remove( Long id) {
        if (orderStatisticsService.remove(id) > 0) {
			return ResponseUtils.success();
        }
		return ResponseUtils.fail();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("bus:orderStatistics:batchRemove")
    public BaseResponse<Void> remove(@RequestParam("ids[]") Long[] ids) {
            orderStatisticsService.batchRemove(ids);
		return ResponseUtils.success();
    }

}
