package com.gui.module.bus.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gui.base.BasePageResponse;
import com.gui.base.BaseResponse;
import com.gui.utils.ResponseUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gui.module.bus.domain.LineDO;
import com.gui.module.bus.service.LineService;
import com.gui.module.common.utils.Query;

/**
 * @author peigui.huang
 * @email 1157688065@qq.com
 * @date 2020-05-29 21:30:13
 */

@Controller
@RequestMapping("/bus/line")
public class LineController {
    @Autowired
    private LineService lineService;

    @GetMapping()
    @RequiresPermissions("bus:line:line")
    String Line() {
        return "bus/line/line";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("bus:line:line")
    public BasePageResponse list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<LineDO> lineList = lineService.list(query);
        int total = lineService.count(query);
        return ResponseUtils.buildPageSuccess(total, lineList);
    }

    @GetMapping("/add")
    @RequiresPermissions("bus:line:add")
    String add() {
        return "bus/line/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("bus:line:edit")
    String edit(@PathVariable("id") Long id, Model model) {
        LineDO line = lineService.get(id);
        model.addAttribute("line", line);
        return "bus/line/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("bus:line:add")
    public BaseResponse<Void> save(LineDO line) {
        line.setCreateTime(new Date());
        line.setUpdateTime(new Date());
        if (lineService.save(line) > 0) {
			return ResponseUtils.success();
        }
		return ResponseUtils.fail();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("bus:line:edit")
    public BaseResponse<Void> update(LineDO line) {
        line.setUpdateTime(new Date());
        lineService.update(line);
		return ResponseUtils.success();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("bus:line:remove")
    public BaseResponse<Void> remove(Long id) {
        if (lineService.remove(id) > 0) {
			return ResponseUtils.success();
        }
		return ResponseUtils.fail();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("bus:line:batchRemove")
    public BaseResponse<Void> remove(@RequestParam("ids[]") Long[] ids) {
        lineService.batchRemove(ids);
		return ResponseUtils.success();
    }

}
