package com.gui.module.device.controller;


import com.gui.base.BasePageResponse;
import com.gui.base.BaseResponse;
import com.gui.module.common.utils.Query;
import com.gui.module.device.domain.MerchantDO;
import com.gui.module.device.service.MerchantService;
import com.gui.utils.ResponseUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author peigui.huang
 * @email 1992lcg@163.com
 * @date 2020-01-05 15:16:44
 */
 
@Controller
@RequestMapping("/device/merchant")
public class MerchantController {
	@Autowired
	private MerchantService merchantService;
	
	@GetMapping()
	@RequiresPermissions("device:merchant:merchant")
	String Merchant(){
	    return "device/merchant/merchant";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("device:merchant:merchant")
	public BasePageResponse list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MerchantDO> merchantList = merchantService.list(query);
		int total = merchantService.count(query);
		return ResponseUtils.buildPageSuccess(total,merchantList);
	}

	@GetMapping("/all")
	@ResponseBody
	public List<MerchantDO> listType() {
		return merchantService.list(new HashMap<>(0));
	}
	
	@GetMapping("/add")
	@RequiresPermissions("device:merchant:add")
	String add(){
	    return "device/merchant/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("device:merchant:edit")
	String edit(@PathVariable("id") Long id,Model model){
		MerchantDO merchant = merchantService.get(id);
		model.addAttribute("merchant", merchant);
	    return "device/merchant/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("device:merchant:add")
	public BaseResponse<Void> save(MerchantDO merchant){
		merchant.setCreateTime(new Date());
		merchant.setUpdateTime(new Date());
		if(merchantService.save(merchant)>0){
			return ResponseUtils.success();
		}
		return ResponseUtils.fail();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("device:merchant:edit")
	public BaseResponse<Void> update( MerchantDO merchant){
		merchant.setUpdateTime(new Date());
		merchantService.update(merchant);
		return ResponseUtils.success();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("device:merchant:remove")
	public BaseResponse<Void> remove( Long id){
		if(merchantService.remove(id)>0){
		return ResponseUtils.success();
		}
		return ResponseUtils.fail();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("device:merchant:batchRemove")
	public BaseResponse<Void> remove(@RequestParam("ids[]") Long[] ids){
		merchantService.batchRemove(ids);
		return ResponseUtils.success();
	}
	
}
