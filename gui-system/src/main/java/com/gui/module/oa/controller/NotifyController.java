package com.gui.module.oa.controller;

import com.gui.base.BaseController;
import com.gui.base.BasePageResponse;
import com.gui.base.BaseResponse;
import com.gui.base.Constant;
import com.gui.module.common.domain.DictDO;
import com.gui.module.common.service.DictService;
import com.gui.module.common.utils.Query;
import com.gui.module.oa.domain.NotifyDO;
import com.gui.module.oa.domain.NotifyRecordDO;
import com.gui.module.oa.service.NotifyRecordService;
import com.gui.module.oa.service.NotifyService;
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
 * 通知通告
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-05 17:11:16
 */

@Controller
@RequestMapping("/oa/notify")
public class NotifyController extends BaseController {
	@Autowired
	private NotifyService notifyService;
	@Autowired
	private NotifyRecordService notifyRecordService;
	@Autowired
	private DictService dictService;

	@GetMapping()
	@RequiresPermissions("oa:notify:notify")
	String oaNotify() {
		return "oa/notify/notify";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:notify:notify")
	public BasePageResponse list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<NotifyDO> notifyList = notifyService.list(query);
		int total = notifyService.count(query);
		return ResponseUtils.buildPageSuccess(total,notifyList);
	}

	@GetMapping("/add")
	@RequiresPermissions("oa:notify:add")
	String add() {
		return "oa/notify/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:notify:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		NotifyDO notify = notifyService.get(id);
		List<DictDO> dictDOS = dictService.listByType("oa_notify_type");
		String type = notify.getType();
		for (DictDO dictDO:dictDOS){
			if(type.equals(dictDO.getValue())){
				dictDO.setRemarks("checked");
			}
		}
		model.addAttribute("oaNotifyTypes",dictDOS);
		model.addAttribute("notify", notify);
		return "oa/notify/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:notify:add")
	public BaseResponse<Void> save(NotifyDO notify) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
		}
		notify.setCreateBy(getUserId());
		if (notifyService.save(notify) > 0) {
			return ResponseUtils.success();
		}
		return ResponseUtils.fail();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:notify:edit")
	public BaseResponse<Void> update(NotifyDO notify) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
		}
		notifyService.update(notify);
		return ResponseUtils.success();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("oa:notify:remove")
	public BaseResponse<Void> remove(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (notifyService.remove(id) > 0) {
			return ResponseUtils.success();
		}
		return ResponseUtils.fail();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:notify:batchRemove")
	public BaseResponse<Void> remove(@RequestParam("ids[]") Long[] ids) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
		}
		notifyService.batchRemove(ids);
		return ResponseUtils.success();
	}

	@ResponseBody
	@GetMapping("/message")
	BasePageResponse message() {
		Map<String, Object> params = new HashMap<>(16);
		params.put("offset", 0);
		params.put("limit", 3);
		Query query = new Query(params);
        query.put("userId", getUserId());
        query.put("isRead",Constant.OA_NOTIFY_READ_NO);
		return notifyService.selfList(query);
	}

	@GetMapping("/selfNotify")
	String selefNotify() {
		return "oa/notify/selfNotify";
	}

	@ResponseBody
	@GetMapping("/selfList")
	BasePageResponse selfList(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		query.put("userId", getUserId());

		return notifyService.selfList(query);
	}

	@GetMapping("/read/{id}")
	@RequiresPermissions("oa:notify:edit")
	String read(@PathVariable("id") Long id, Model model) {
		NotifyDO notify = notifyService.get(id);
		//更改阅读状态
		NotifyRecordDO notifyRecordDO = new NotifyRecordDO();
		notifyRecordDO.setNotifyId(id);
		notifyRecordDO.setUserId(getUserId());
		notifyRecordDO.setReadDate(new Date());
		notifyRecordDO.setIsRead(Constant.OA_NOTIFY_READ_YES);
		notifyRecordService.changeRead(notifyRecordDO);
		model.addAttribute("notify", notify);
		return "oa/notify/read";
	}



}
