package com.gui.module.device.controller;


import com.gui.base.BasePageResponse;
import com.gui.base.BaseResponse;
import com.gui.module.common.utils.Query;
import com.gui.module.device.domain.AppDeviceDO;
import com.gui.module.device.service.AppDeviceService;
import com.gui.utils.ResponseUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author peigui.huang
 * @email 1992lcg@163.com
 * @date 2020-01-05 15:16:40
 */
 
@Controller
@RequestMapping("/device/appDevice")
public class AppDeviceController {
	@Autowired
	private AppDeviceService appDeviceService;
	
	@GetMapping()
	@RequiresPermissions("device:appDevice:appDevice")
	String AppDevice(){
	    return "device/appDevice/appDevice";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("device:appDevice:appDevice")
	public BasePageResponse list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AppDeviceDO> appDeviceList = appDeviceService.list(query);
		int total = appDeviceService.count(query);
		return ResponseUtils.buildPageSuccess(total,appDeviceList);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("device:appDevice:add")
	String add(){
	    return "device/appDevice/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("device:appDevice:edit")
	String edit(@PathVariable("id") Long id,Model model){
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
	public BaseResponse<Void> save( AppDeviceDO appDevice){
		appDevice.setCreateTime(new Date());
		appDevice.setUpdateTime(new Date());
		if(appDeviceService.save(appDevice)>0){
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
	public BaseResponse<Void> update(AppDeviceDO appDevice){
		appDevice.setUpdateTime(new Date());
		appDeviceService.update(appDevice);
		return ResponseUtils.success();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("device:appDevice:remove")
	public BaseResponse<Void> remove( Long id){
		if(appDeviceService.remove(id)>0){
		return ResponseUtils.success();
		}
		return ResponseUtils.fail();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("device:appDevice:batchRemove")
	public BaseResponse<Void> remove(@RequestParam("ids[]") Long[] ids){
		appDeviceService.batchRemove(ids);
		return ResponseUtils.success();
	}
	
}
