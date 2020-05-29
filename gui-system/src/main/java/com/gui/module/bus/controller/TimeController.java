package com.gui.module.bus.controller;

import java.util.List;
import java.util.Map;

import com.gui.base.BasePageResponse;
import com.gui.base.BaseResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gui.module.bus.domain.TimeDO;
import com.gui.module.bus.service.TimeService;
import com.gui.module.common.utils.Query;
import org.springframework.stereotype.Controller;
import com.gui.utils.ResponseUtils;

/**
 * 
 *
 * @author peigui.huang
 * @email 1157688065@qq.com
 * @date 2020-05-29 22:17:27
 */

@Controller
@RequestMapping("/bus/time")
public class TimeController {
    @Autowired
    private TimeService timeService;

    @GetMapping()
    @RequiresPermissions("bus:time:time")
    String Time() {
        return "bus/time/time";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("bus:time:time")
    public BasePageResponse list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<TimeDO> timeList = timeService.list(query);
        int total = timeService.count(query);
        return ResponseUtils.buildPageSuccess(total, timeList);
    }

    @GetMapping("/add")
    @RequiresPermissions("bus:time:add")
    String add() {
        return "bus/time/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("bus:time:edit")
    String edit(@PathVariable("id") Long id, Model model) {
            TimeDO time = timeService.get(id);
        model.addAttribute("time", time);
        return "bus/time/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("bus:time:add")
    public BaseResponse<Void> save( TimeDO time) {
        if (timeService.save(time) > 0) {
            return ResponseUtils.success();
        }
        return ResponseUtils.fail();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("bus:time:edit")
    public BaseResponse<Void> update( TimeDO time) {
            timeService.update(time);
        return ResponseUtils.success();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("bus:time:remove")
    public BaseResponse<Void> remove( Long id) {
        if (timeService.remove(id) > 0) {
			return ResponseUtils.success();
        }
		return ResponseUtils.fail();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("bus:time:batchRemove")
    public BaseResponse<Void> remove(@RequestParam("ids[]") Long[] ids) {
            timeService.batchRemove(ids);
		return ResponseUtils.success();
    }

}
