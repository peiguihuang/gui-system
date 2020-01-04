package com.gui.module.system.controller;

import com.gui.base.BaseController;
import com.gui.base.BaseResponse;
import com.gui.base.Constant;
import com.gui.constants.SecurityPermissionConstants;
import com.gui.module.common.annotation.Log;
import com.gui.module.common.domain.Tree;
import com.gui.module.system.domain.MenuDO;
import com.gui.module.system.service.MenuService;
import com.gui.utils.ResponseUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author gui
 */
@RequestMapping("/sys/menu")
@Controller
public class MenuController extends BaseController {
	String prefix = "system/menu";
	@Autowired
    MenuService menuService;

	@RequiresPermissions(SecurityPermissionConstants.System.QUERY_MENU_LIST)
	@GetMapping()
	String menu(Model model) {
		return prefix+"/menu";
	}

	@RequiresPermissions(SecurityPermissionConstants.System.QUERY_MENU_LIST)
	@RequestMapping("/list")
	@ResponseBody
	List<MenuDO> list(@RequestParam Map<String, Object> params) {
		List<MenuDO> menus = menuService.list(params);
		return menus;
	}

	@Log("添加菜单")
	@RequiresPermissions(SecurityPermissionConstants.System.ADD_MENU)
	@GetMapping("/add/{pId}")
	String add(Model model, @PathVariable("pId") Long pId) {
		model.addAttribute("pId", pId);
		if (pId == 0) {
			model.addAttribute("pName", "根目录");
		} else {
			model.addAttribute("pName", menuService.get(pId).getName());
		}
		return prefix + "/add";
	}

	@Log("编辑菜单")
	@RequiresPermissions(SecurityPermissionConstants.System.EDIT_MENU)
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") Long id) {
		MenuDO mdo = menuService.get(id);
		Long pId = mdo.getParentId();
		model.addAttribute("pId", pId);
		if (pId == 0) {
			model.addAttribute("pName", "根目录");
		} else {
			model.addAttribute("pName", menuService.get(pId).getName());
		}
		model.addAttribute("menu", mdo);
		return prefix+"/edit";
	}

	@Log("保存菜单")
	@RequiresPermissions(SecurityPermissionConstants.System.ADD_MENU)
	@PostMapping("/save")
	@ResponseBody
	BaseResponse<Void> save(MenuDO menu) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (menuService.save(menu) > 0) {
			return ResponseUtils.success();
		} else {
			return ResponseUtils.fail(1, "保存失败");
		}
	}

	@Log("更新菜单")
	@RequiresPermissions(SecurityPermissionConstants.System.EDIT_MENU)
	@PostMapping("/update")
	@ResponseBody
	BaseResponse<Void> update(MenuDO menu) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (menuService.update(menu) > 0) {
			return ResponseUtils.success();
		} else {
			return ResponseUtils.fail(1, "更新失败");
		}
	}

	@Log("删除菜单")
	@RequiresPermissions(SecurityPermissionConstants.System.REMOVE_MENU)
	@PostMapping("/remove")
	@ResponseBody
	BaseResponse<Void> remove(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (menuService.remove(id) > 0) {
			return ResponseUtils.success();
		} else {
			return ResponseUtils.fail(1, "删除失败");
		}
	}

	@GetMapping("/tree")
	@ResponseBody
	Tree<MenuDO> tree() {
		Tree<MenuDO>  tree = menuService.getTree();
		return tree;
	}

	@GetMapping("/tree/{roleId}")
	@ResponseBody
	Tree<MenuDO> tree(@PathVariable("roleId") Long roleId) {
		Tree<MenuDO> tree = menuService.getTree(roleId);
		return tree;
	}
}
