package com.gui.module.device.controller;


import com.gui.base.BasePageResponse;
import com.gui.base.BaseResponse;
import com.gui.module.common.utils.Query;
import com.gui.module.device.domain.MerchantDO;
import com.gui.module.device.domain.MerchantDeviceDO;
import com.gui.module.device.service.MerchantDeviceService;
import com.gui.module.device.service.MerchantService;
import com.gui.utils.ResponseUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author peigui.huang
 * @email 1992lcg@163.com
 * @date 2020-01-05 15:16:46
 */

@Controller
@RequestMapping("/device/merchantDevice")
public class MerchantDeviceController {
    @Autowired
    private MerchantDeviceService merchantDeviceService;

    @Autowired
    private MerchantService merchantService;

    @GetMapping()
    @RequiresPermissions("device:merchantDevice:merchantDevice")
    String MerchantDevice() {
        return "device/merchantDevice/merchantDevice";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("device:merchantDevice:merchantDevice")
    public BasePageResponse list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<MerchantDeviceDO> merchantDeviceList = merchantDeviceService.list(query);
        if (!CollectionUtils.isEmpty(merchantDeviceList)) {
            for (int i = 0; i < merchantDeviceList.size(); i++) {
                MerchantDO merchantDO = merchantService.get(merchantDeviceList.get(i).getMerchantId());
                merchantDeviceList.get(i).setMerchantName(merchantDO.getMerchantName());
            }

        }
        int total = merchantDeviceService.count(query);

        return ResponseUtils.buildPageSuccess(total, merchantDeviceList);
    }

    @GetMapping("/add")
    @RequiresPermissions("device:merchantDevice:add")
    String add() {
        return "device/merchantDevice/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("device:merchantDevice:edit")
    String edit(@PathVariable("id") Long id, Model model) {
        MerchantDeviceDO merchantDevice = merchantDeviceService.get(id);
        model.addAttribute("merchantDevice", merchantDevice);
        return "device/merchantDevice/edit";
    }


    @GetMapping("/all")
    @ResponseBody
    public List<MerchantDeviceDO> listType() {
        return merchantDeviceService.list(new HashMap<>(0));
    }


    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("device:merchantDevice:add")
    public BaseResponse<Void> save(MerchantDeviceDO merchantDevice) {
        merchantDevice.setCreateTime(new Date());
        merchantDevice.setUpdateTime(new Date());
        if (merchantDeviceService.save(merchantDevice) > 0) {
            return ResponseUtils.success();
        }
        return ResponseUtils.fail();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("device:merchantDevice:edit")
    public BaseResponse<Void> update(MerchantDeviceDO merchantDevice) {
        merchantDevice.setUpdateTime(new Date());
        merchantDeviceService.update(merchantDevice);
        return ResponseUtils.success();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("device:merchantDevice:remove")
    public BaseResponse<Void> remove(Long id) {
        if (merchantDeviceService.remove(id) > 0) {
            return ResponseUtils.success();
        }
        return ResponseUtils.fail();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("device:merchantDevice:batchRemove")
    public BaseResponse<Void> remove(@RequestParam("ids[]") Long[] ids) {
        merchantDeviceService.batchRemove(ids);
        return ResponseUtils.success();
    }

}
