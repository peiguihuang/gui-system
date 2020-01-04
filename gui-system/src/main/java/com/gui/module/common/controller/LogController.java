package com.gui.module.common.controller;

import com.gui.base.BaseResponse;
import com.gui.module.common.domain.LogDO;
import com.gui.module.common.domain.PageDO;
import com.gui.module.common.service.LogService;
import com.gui.module.common.utils.Query;
import com.gui.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/common/log")
@Controller
public class LogController {
	@Autowired
    LogService logService;
	String prefix = "common/log";

	@GetMapping()
	String log() {
		return prefix + "/log";
	}

	@ResponseBody
	@GetMapping("/list")
    PageDO<LogDO> list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		PageDO<LogDO> page = logService.queryList(query);
		return page;
	}
	
	@ResponseBody
	@PostMapping("/remove")
	BaseResponse<Void> remove(Long id) {
		if (logService.remove(id)>0) {
			return ResponseUtils.success();
		}
		return ResponseUtils.fail();
	}

	@ResponseBody
	@PostMapping("/batchRemove")
	BaseResponse<Void> batchRemove(@RequestParam("ids[]") Long[] ids) {
		int r = logService.batchRemove(ids);
		if (r > 0) {
			return ResponseUtils.success();
		}
		return ResponseUtils.fail();
	}
}
