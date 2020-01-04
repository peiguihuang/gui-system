package com.gui.module.activiti.controller;

import com.gui.base.BaseController;
import com.gui.base.BasePageResponse;
import com.gui.base.BaseResponse;
import com.gui.base.Constant;
import com.gui.module.activiti.domain.SalaryDO;
import com.gui.module.activiti.service.SalaryService;
import com.gui.module.activiti.utils.ActivitiUtils;
import com.gui.module.common.utils.Query;
import com.gui.module.common.utils.ShiroUtils;
import com.gui.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 审批流程测试表
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-11-25 13:33:16
 */

@Controller
@RequestMapping("/act/salary")
public class SalaryController extends BaseController {
    @Autowired
    private SalaryService salaryService;
    @Autowired
    ActivitiUtils activitiUtils;

    @GetMapping()
    String Salary() {
        return "activiti/salary/salary";
    }

    @ResponseBody
    @GetMapping("/list")
    public BasePageResponse list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<SalaryDO> salaryList = salaryService.list(query);
        int total = salaryService.count(query);
        return ResponseUtils.buildPageSuccess(total,salaryList);
    }

    @GetMapping("/form")
    String add() {
        return "act/salary/add";
    }

    @GetMapping("/form/{taskId}")
    String edit(@PathVariable("taskId") String taskId, Model model) {
        SalaryDO salary = salaryService.get(activitiUtils.getBusinessKeyByTaskId(taskId));
        salary.setTaskId(taskId);
        model.addAttribute("salary", salary);
        return "act/salary/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public BaseResponse<Void> saveOrUpdate(SalaryDO salary) {
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
        }
        salary.setCreateDate(new Date());
        salary.setUpdateDate(new Date());
        salary.setCreateBy(ShiroUtils.getUserId().toString());
        salary.setUpdateBy(ShiroUtils.getUserId().toString());
        salary.setDelFlag("1");
        if (salaryService.save(salary) > 0) {
            return ResponseUtils.success();
        }
        return ResponseUtils.fail();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public BaseResponse<Void> update(SalaryDO salary) {
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
        }
        String taskKey = activitiUtils.getTaskByTaskId(salary.getTaskId()).getTaskDefinitionKey();
        if ("audit2".equals(taskKey)) {
            salary.setHrText(salary.getTaskComment());
        } else if ("audit3".equals(taskKey)) {
            salary.setLeadText(salary.getTaskComment());
        } else if ("audit4".equals(taskKey)) {
            salary.setMainLeadText(salary.getTaskComment());
        } else if("apply_end".equals(salary.getTaskComment())){
            //流程完成，兑现
        }
        salaryService.update(salary);
        return ResponseUtils.success();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public BaseResponse<Void> remove(String id) {
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
        }
        if (salaryService.remove(id) > 0) {
            return ResponseUtils.success();
        }
        return ResponseUtils.fail();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    public BaseResponse<Void> remove(@RequestParam("ids[]") String[] ids) {
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
        }
        salaryService.batchRemove(ids);
        return ResponseUtils.success();
    }

}
