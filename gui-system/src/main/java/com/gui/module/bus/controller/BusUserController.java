package com.gui.module.bus.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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

import com.gui.module.bus.domain.UserDO;
import com.gui.module.bus.service.BusUserService;
import com.gui.module.common.utils.Query;
import org.springframework.stereotype.Controller;
import com.gui.utils.ResponseUtils;

/**
 * 
 *
 * @author peigui.huang
 * @email 1157688065@qq.com
 * @date 2020-05-30 09:50:30
 */

@Controller
@RequestMapping("/bus/user")
public class BusUserController {
    @Autowired
    private BusUserService busUserService;

    @GetMapping()
    @RequiresPermissions("bus:user:user")
    String User() {
        return "bus/user/user";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("bus:user:user")
    public BasePageResponse list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<UserDO> userList = busUserService.list(query);
        int total = busUserService.count(query);
        return ResponseUtils.buildPageSuccess(total, userList);
    }

    @GetMapping("/add")
    @RequiresPermissions("bus:user:add")
    String add() {
        return "bus/user/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("bus:user:edit")
    String edit(@PathVariable("id") Long id, Model model) {
            UserDO user = busUserService.get(id);
        model.addAttribute("user", user);
        return "bus/user/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("bus:user:add")
    public BaseResponse<Void> save( UserDO user) {
        if (busUserService.save(user) > 0) {
            return ResponseUtils.success();
        }
        return ResponseUtils.fail();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("bus:user:edit")
    public BaseResponse<Void> update( UserDO user) {
            busUserService.update(user);
        return ResponseUtils.success();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("bus:user:remove")
    public BaseResponse<Void> remove( Long id) {
        if (busUserService.remove(id) > 0) {
			return ResponseUtils.success();
        }
		return ResponseUtils.fail();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("bus:user:batchRemove")
    public BaseResponse<Void> remove(@RequestParam("ids[]") Long[] ids) {
            busUserService.batchRemove(ids);
		return ResponseUtils.success();
    }

}
