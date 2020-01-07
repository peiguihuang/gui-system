package com.gui.module.device.controller;


import com.gui.base.BasePageResponse;
import com.gui.base.BaseResponse;
import com.gui.module.common.utils.Query;
import com.gui.module.device.domain.AppDeviceDO;
import com.gui.module.device.domain.AppVerisonDO;
import com.gui.module.device.domain.MerchantDO;
import com.gui.module.device.domain.MerchantDeviceDO;
import com.gui.module.device.service.AppDeviceService;
import com.gui.module.device.service.AppVerisonService;
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
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author peigui.huang
 * @email 1992lcg@163.com
 * @date 2020-01-05 15:16:40
 */

@Controller
@RequestMapping("/device/appDevice")
public class AppDeviceController {
    @Autowired
    private AppDeviceService appDeviceService;

    @Autowired
    private AppVerisonService appVerisonService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private MerchantDeviceService merchantDeviceService;


    @GetMapping()
    @RequiresPermissions("device:appDevice:appDevice")
    String AppDevice() {
        return "device/appDevice/appDevice";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("device:appDevice:appDevice")
    public BasePageResponse list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<AppDeviceDO> appDeviceList = appDeviceService.list(query);
        if (!CollectionUtils.isEmpty(appDeviceList)) {
            for (int i = 0; i < appDeviceList.size(); i++) {

                if (Objects.nonNull(appDeviceList.get(i).getAppId())) {
                    AppVerisonDO appVerisonDO = appVerisonService.get(appDeviceList.get(i).getAppId());
                    appDeviceList.get(i).setAppVersion(appVerisonDO.getAppVersion());
                    appDeviceList.get(i).setAppShowVersion(appVerisonDO.getAppShowVersion());
                }

                if (Objects.nonNull(appDeviceList.get(i).getDeviceId())) {
                    MerchantDeviceDO merchantDeviceDO = merchantDeviceService.get(appDeviceList.get(i).getDeviceId());
                    appDeviceList.get(i).setDeviceName(merchantDeviceDO.getDeviceName());
                }

                if (Objects.nonNull(appDeviceList.get(i).getMerchantId())) {
                    MerchantDO merchantDO = merchantService.get(appDeviceList.get(i).getMerchantId());
                    appDeviceList.get(i).setMerchantName(merchantDO.getMerchantName());
                }
                if (appDeviceList.get(i).getType()==0){
                    appDeviceList.get(i).setTypeName("全部升级");
                }else if (appDeviceList.get(i).getType() ==1){
                    appDeviceList.get(i).setTypeName("针对商户升级");
                }else {
                    appDeviceList.get(i).setTypeName("针对单台设备升级");
                }

                if (appDeviceList.get(i).getStatus()==0){
                    appDeviceList.get(i).setStatusName("生效");
                }else {
                    appDeviceList.get(i).setStatusName("无效");
                }
            }
        }
        int total = appDeviceService.count(query);
        return ResponseUtils.buildPageSuccess(total, appDeviceList);
    }

    @GetMapping("/add")
    @RequiresPermissions("device:appDevice:add")
    String add() {
        return "device/appDevice/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("device:appDevice:edit")
    String edit(@PathVariable("id") Long id, Model model) {
        AppDeviceDO appDevice = appDeviceService.get(id);
        model.addAttribute("appDevice", appDevice);
        return "device/appDevice/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("device:appDevice:add")
    public BaseResponse<Void> save(AppDeviceDO appDevice) {
        appDevice.setCreateTime(new Date());
        appDevice.setUpdateTime(new Date());
        if (appDeviceService.save(appDevice) > 0) {
            return ResponseUtils.success();
        }
        return ResponseUtils.fail();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("device:appDevice:edit")
    public BaseResponse<Void> update(AppDeviceDO appDevice) {
        appDevice.setUpdateTime(new Date());
        appDeviceService.update(appDevice);
        return ResponseUtils.success();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("device:appDevice:remove")
    public BaseResponse<Void> remove(Long id) {
        if (appDeviceService.remove(id) > 0) {
            return ResponseUtils.success();
        }
        return ResponseUtils.fail();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("device:appDevice:batchRemove")
    public BaseResponse<Void> remove(@RequestParam("ids[]") Long[] ids) {
        appDeviceService.batchRemove(ids);
        return ResponseUtils.success();
    }

}
