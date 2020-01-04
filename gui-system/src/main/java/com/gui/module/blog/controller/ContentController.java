package com.gui.module.blog.controller;

import com.gui.base.BaseController;
import com.gui.base.BasePageResponse;
import com.gui.base.BaseResponse;
import com.gui.base.Constant;
import com.gui.constants.SecurityPermissionConstants;
import com.gui.module.blog.domain.ContentDO;
import com.gui.module.blog.service.ContentService;
import com.gui.module.common.utils.Query;
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
 * 文章内容
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-09 10:03:34
 */
@Controller
@RequestMapping("/blog/bContent")
public class ContentController extends BaseController {
	@Autowired
    ContentService bContentService;

	@GetMapping()
	@RequiresPermissions("blog:bContent:bContent")
	String bContent() {
		return "blog/bContent/bContent";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions(SecurityPermissionConstants.Blog.QUERY_BLOG_LIST)
	public BasePageResponse list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<ContentDO> bContentList = bContentService.list(query);
		int total = bContentService.count(query);
		return ResponseUtils.buildPageSuccess(total,bContentList);
	}

	@GetMapping("/add")
	@RequiresPermissions(SecurityPermissionConstants.Blog.ADD_BLOG)
	String add() {
		return "blog/bContent/add";
	}

	@GetMapping("/edit/{cid}")
	@RequiresPermissions(SecurityPermissionConstants.Blog.EDIT_BLOG)
	String edit(@PathVariable("cid") Long cid, Model model) {
		ContentDO bContentDO = bContentService.get(cid);
		model.addAttribute("bContent", bContentDO);
		return "blog/bContent/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@RequiresPermissions(SecurityPermissionConstants.Blog.ADD_BLOG)
	@PostMapping("/save")
	public BaseResponse<Long> save(ContentDO bContent) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (bContent.getAllowComment() == null) {
			bContent.setAllowComment(0);
		}
		if (bContent.getAllowFeed() == null) {
			bContent.setAllowFeed(0);
		}
		if(null==bContent.getType()) {
			bContent.setType("article");
		}
		bContent.setGtmCreate(new Date());
		bContent.setGtmModified(new Date());
		int count;
		if (bContent.getCid() == null || "".equals(bContent.getCid())) {
			count = bContentService.save(bContent);
		} else {
			count = bContentService.update(bContent);
		}
		if (count > 0) {
			return ResponseUtils.success(bContent.getCid());
		}
		return ResponseUtils.fail();
	}

	/**
	 * 修改
	 */
	@RequiresPermissions(SecurityPermissionConstants.Blog.EDIT_BLOG)
	@ResponseBody
	@RequestMapping("/update")
	public BaseResponse<Void> update( ContentDO bContent) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
		}
		bContent.setGtmCreate(new Date());
		bContentService.update(bContent);
		return ResponseUtils.success();
	}

	/**
	 * 删除
	 */
	@RequiresPermissions(SecurityPermissionConstants.Blog.REMOVE_BLOG)
	@PostMapping("/remove")
	@ResponseBody
	public BaseResponse<Void> remove(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (bContentService.remove(id) > 0) {
			return ResponseUtils.success();
		}
		return ResponseUtils.fail();
	}

	/**
	 * 删除
	 */
	@RequiresPermissions(SecurityPermissionConstants.Blog.BATCHREMOVE_BLOG)
	@PostMapping("/batchRemove")
	@ResponseBody
	public BaseResponse<Void> remove(@RequestParam("ids[]") Long[] cids) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
		}
		bContentService.batchRemove(cids);
		return ResponseUtils.success();
	}
}
