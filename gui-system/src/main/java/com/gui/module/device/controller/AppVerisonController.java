package com.gui.module.device.controller;


import com.gui.base.BasePageResponse;
import com.gui.base.BaseResponse;
import com.gui.module.common.utils.Query;
import com.gui.module.device.domain.AppVerisonDO;
import com.gui.module.device.service.AppVerisonService;
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
 * @date 2020-01-05 15:16:43
 */
 
@Controller
@RequestMapping("/device/appVerison")
public class AppVerisonController {
	@Autowired
	private AppVerisonService appVerisonService;
	
	@GetMapping()
	@RequiresPermissions("device:appVerison:appVerison")
	String AppVerison(){
	    return "device/appVerison/appVerison";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("device:appVerison:appVerison")
	public BasePageResponse list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AppVerisonDO> appVerisonList = appVerisonService.list(query);
		int total = appVerisonService.count(query);
		return ResponseUtils.buildPageSuccess(total,appVerisonList);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("device:appVerison:add")
	String add(){
	    return "device/appVerison/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("device:appVerison:edit")
	String edit(@PathVariable("id") Long id,Model model){
		AppVerisonDO appVerison = appVerisonService.get(id);
		model.addAttribute("appVerison", appVerison);
	    return "device/appVerison/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("device:appVerison:add")
	public BaseResponse<Void> save( AppVerisonDO appVerison){
		appVerison.setCreateTime(new Date());
		appVerison.setUpdateTime(new Date());
		if(appVerisonService.save(appVerison)>0){
			return ResponseUtils.success();
		}
		return ResponseUtils.fail();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("device:appVerison:edit")
	public BaseResponse<Void> update(AppVerisonDO appVerison){
		appVerison.setUpdateTime(new Date());
		appVerisonService.update(appVerison);
		return ResponseUtils.success();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("device:appVerison:remove")
	public BaseResponse<Void> remove( Long id){
		if(appVerisonService.remove(id)>0){
		return ResponseUtils.success();
		}
		return ResponseUtils.fail();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("device:appVerison:batchRemove")
	public BaseResponse<Void> remove(@RequestParam("ids[]") Long[] ids){
		appVerisonService.batchRemove(ids);
		return ResponseUtils.success();
	}
	
}
