package com.gui.module.bus.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.util.CollectionUtils;
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

import com.gui.module.bus.domain.SubOrderDO;
import com.gui.module.bus.service.SubOrderService;
import com.gui.module.common.utils.Query;
import org.springframework.stereotype.Controller;
import com.gui.utils.ResponseUtils;

/**
 * 
 *
 * @author peigui.huang
 * @email 1157688065@qq.com
 * @date 2020-05-30 17:45:33
 */

@Controller
@RequestMapping("/bus/subOrder")
public class SubOrderController {
    @Autowired
    private SubOrderService subOrderService;

    @GetMapping()
    @RequiresPermissions("bus:subOrder:subOrder")
    String SubOrder() {
        return "bus/subOrder/subOrder";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("bus:subOrder:subOrder")
    public BasePageResponse list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<SubOrderDO> subOrderList = subOrderService.list(query);
        if (!CollectionUtils.isEmpty(subOrderList)){
            subOrderList.forEach(subOrderDO -> {
                if (subOrderDO.getPayStatus() == 0){
//                    0:待支付 1:已支付 2:已取消
                    subOrderDO.setPayStatusStr("待支付");
                }else if (subOrderDO.getPayStatus() == 1){
                    subOrderDO.setPayStatusStr("已支付");

                }else {
                    subOrderDO.setPayStatusStr("已取消");

                }
            });
        }
        int total = subOrderService.count(query);
        return ResponseUtils.buildPageSuccess(total, subOrderList);
    }

    @GetMapping("/add")
    @RequiresPermissions("bus:subOrder:add")
    String add() {
        return "bus/subOrder/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("bus:subOrder:edit")
    String edit(@PathVariable("id") Long id, Model model) {
            SubOrderDO subOrder = subOrderService.get(id);
        model.addAttribute("subOrder", subOrder);
        return "bus/subOrder/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("bus:subOrder:add")
    public BaseResponse<Void> save( SubOrderDO subOrder) {
        if (subOrderService.save(subOrder) > 0) {
            return ResponseUtils.success();
        }
        return ResponseUtils.fail();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("bus:subOrder:edit")
    public BaseResponse<Void> update( SubOrderDO subOrder) {
            subOrderService.update(subOrder);
        return ResponseUtils.success();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("bus:subOrder:remove")
    public BaseResponse<Void> remove( Long id) {
        if (subOrderService.remove(id) > 0) {
			return ResponseUtils.success();
        }
		return ResponseUtils.fail();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("bus:subOrder:batchRemove")
    public BaseResponse<Void> remove(@RequestParam("ids[]") Long[] ids) {
            subOrderService.batchRemove(ids);
		return ResponseUtils.success();
    }

}
