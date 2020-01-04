package com.gui.module.system.controller;

import com.gui.base.BaseController;
import com.gui.base.BasePageResponse;
import com.gui.base.BaseResponse;
import com.gui.base.Constant;
import com.gui.constants.SecurityPermissionConstants;
import com.gui.module.common.annotation.Log;
import com.gui.module.common.domain.Tree;
import com.gui.module.common.service.DictService;
import com.gui.module.common.utils.MD5Utils;
import com.gui.module.common.utils.Query;
import com.gui.module.system.domain.DeptDO;
import com.gui.module.system.domain.RoleDO;
import com.gui.module.system.domain.UserDO;
import com.gui.module.system.service.RoleService;
import com.gui.module.system.service.UserService;
import com.gui.module.system.vo.UserImgVO;
import com.gui.module.system.vo.UserVO;
import com.gui.utils.ResponseUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequestMapping("/sys/user")
@Controller
public class UserController extends BaseController {
	private String prefix="system/user"  ;
	@Autowired
    UserService userService;
	@Autowired
    RoleService roleService;
	@Autowired
	DictService dictService;
	@RequiresPermissions(SecurityPermissionConstants.System.QUERY_USER_LIST)
	@GetMapping("")
	String user(Model model) {
		return prefix + "/user";
	}

	@GetMapping("/list")
	@ResponseBody
	BasePageResponse list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<UserDO> sysUserList = userService.list(query);
		int total = userService.count(query);
		return ResponseUtils.buildPageSuccess(total,sysUserList);
	}

	@RequiresPermissions(SecurityPermissionConstants.System.ADD_USER)
	@Log("添加用户")
	@GetMapping("/add")
	String add(Model model) {
		List<RoleDO> roles = roleService.list();
		model.addAttribute("roles", roles);
		return prefix + "/add";
	}

	@RequiresPermissions(SecurityPermissionConstants.System.EDIT_USER)
	@Log("编辑用户")
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") Long id) {
		UserDO userDO = userService.get(id);
		model.addAttribute("user", userDO);
		List<RoleDO> roles = roleService.list(id);
		model.addAttribute("roles", roles);
		return prefix+"/edit";
	}

	@RequiresPermissions(SecurityPermissionConstants.System.ADD_USER)
	@Log("保存用户")
	@PostMapping("/save")
	@ResponseBody
	BaseResponse<Void> save(UserDO user) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
		}
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		if (userService.save(user) > 0) {
			return ResponseUtils.success();
		}
		return ResponseUtils.fail();
	}

	@RequiresPermissions(SecurityPermissionConstants.System.EDIT_USER)
	@Log("更新用户")
	@PostMapping("/update")
	@ResponseBody
	BaseResponse<Void> update(UserDO user) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (userService.update(user) > 0) {
			return ResponseUtils.success();
		}
		return ResponseUtils.fail();
	}


	@RequiresPermissions(SecurityPermissionConstants.System.EDIT_USER)
	@Log("更新用户")
	@PostMapping("/updatePeronal")
	@ResponseBody
	BaseResponse<Void> updatePeronal(UserDO user) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (userService.updatePersonal(user) > 0) {
			return ResponseUtils.success();
		}
		return ResponseUtils.fail();
	}


	@RequiresPermissions(SecurityPermissionConstants.System.REMOVE_USER)
	@Log("删除用户")
	@PostMapping("/remove")
	@ResponseBody
	BaseResponse<Void> remove(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (userService.remove(id) > 0) {
			return ResponseUtils.success();
		}
		return ResponseUtils.fail();
	}

	@RequiresPermissions(SecurityPermissionConstants.System.BATCHREMOVE_USER)
	@Log("批量删除用户")
	@PostMapping("/batchRemove")
	@ResponseBody
	BaseResponse<Void> batchRemove(@RequestParam("ids[]") Long[] userIds) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
		}
		int r = userService.batchremove(userIds);
		if (r > 0) {
			return ResponseUtils.success();
		}
		return ResponseUtils.fail();
	}

	@PostMapping("/exit")
	@ResponseBody
	boolean exit(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !userService.exit(params);
	}

	@RequiresPermissions(SecurityPermissionConstants.System.USER_RESETPWD)
	@Log("请求更改用户密码")
	@GetMapping("/resetPwd/{id}")
	String resetPwd(@PathVariable("id") Long userId, Model model) {

		UserDO userDO = new UserDO();
		userDO.setUserId(userId);
		model.addAttribute("user", userDO);
		return prefix + "/reset_pwd";
	}

	@Log("提交更改用户密码")
	@PostMapping("/resetPwd")
	@ResponseBody
	BaseResponse<Void> resetPwd(UserVO userVO) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
		}
		try{
			userService.resetPwd(userVO,getUser());
			return ResponseUtils.success();
		}catch (Exception e){
			return ResponseUtils.fail(1,e.getMessage());
		}

	}
	@RequiresPermissions(SecurityPermissionConstants.System.USER_RESETPWD)
	@Log("admin提交更改用户密码")
	@PostMapping("/adminResetPwd")
	@ResponseBody
	BaseResponse<Void> adminResetPwd(UserVO userVO) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
		}
		try{
			userService.adminResetPwd(userVO);
			return ResponseUtils.success();
		}catch (Exception e){
			return ResponseUtils.fail(1,e.getMessage());
		}

	}
	@GetMapping("/tree")
	@ResponseBody
	public Tree<DeptDO> tree() {
		Tree<DeptDO> tree = new Tree<DeptDO>();
		tree = userService.getTree();
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/userTree";
	}

	@GetMapping("/personal")
	String personal(Model model) {
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		model.addAttribute("hobbyList",dictService.getHobbyList(userDO));
		model.addAttribute("sexList",dictService.getSexList());
		return prefix + "/personal";
	}
	@ResponseBody
	@PostMapping("/uploadImg")
	BaseResponse<UserImgVO> uploadImg(@RequestParam("avatar_file") MultipartFile file, String avatar_data, HttpServletRequest request) {
		if ("test".equals(getUsername())) {
			return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
		}
		UserImgVO result;
		try {
			result = userService.updatePersonalImg(file, avatar_data, getUserId());
		} catch (Exception e) {
			return ResponseUtils.fail("更新图像失败！");
		}
		if(Objects.nonNull(result)){
			return ResponseUtils.success(result);
		}else {
			return ResponseUtils.fail("更新图像失败！");
		}
	}
}
