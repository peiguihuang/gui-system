package com.gui.module.common.controller;

import com.gui.base.BaseController;
import com.gui.base.BasePageResponse;
import com.gui.base.BaseResponse;
import com.gui.constants.SecurityPermissionConstants;
import com.gui.module.common.config.GuiConfig;
import com.gui.module.common.domain.FileDO;
import com.gui.module.common.service.FileService;
import com.gui.module.common.utils.FileType;
import com.gui.module.common.utils.FileUtil;
import com.gui.module.common.utils.Query;
import com.gui.utils.ResponseUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件上传
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-19 16:02:20
 */
@Controller
@RequestMapping("/common/sysFile")
public class FileController extends BaseController {

	@Autowired
	private FileService sysFileService;

	@Autowired
	private GuiConfig guiConfig;

	@GetMapping()
	@RequiresPermissions(SecurityPermissionConstants.Common.QUERY_FILE_LIST)
	String sysFile(Model model) {
		Map<String, Object> params = new HashMap<>(16);
		return "common/file/file";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions(SecurityPermissionConstants.Common.QUERY_FILE_LIST)
	public BasePageResponse list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<FileDO> sysFileList = sysFileService.list(query);
		int total = sysFileService.count(query);
		return ResponseUtils.buildPageSuccess(total,sysFileList);
	}

	@GetMapping("/add")
	String add() {
		return "common/sysFile/add";
	}

	@GetMapping("/edit")
	String edit(Long id, Model model) {
		FileDO sysFile = sysFileService.get(id);
		model.addAttribute("sysFile", sysFile);
		return "common/sysFile/edit";
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions(SecurityPermissionConstants.Common.COMMON_INFO)
	public BaseResponse<FileDO> info(@PathVariable("id") Long id) {
		FileDO sysFile = sysFileService.get(id);
		return ResponseUtils.success(sysFile);
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions(SecurityPermissionConstants.Common.COMMON_SAVE)
	public BaseResponse<Void> save(FileDO sysFile) {
		if (sysFileService.save(sysFile) > 0) {
			return ResponseUtils.success();
		}
		return ResponseUtils.fail();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions(SecurityPermissionConstants.Common.COMMON_UPDATE)
	public BaseResponse<Void> update(@RequestBody FileDO sysFile) {
		sysFileService.update(sysFile);

		return ResponseUtils.success();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	public BaseResponse<Void> remove(Long id, HttpServletRequest request) {
		if ("test".equals(getUsername())) {
			return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
		}
		String fileName = guiConfig.getUploadPath() + sysFileService.get(id).getUrl().replace("/files/", "");
		if (sysFileService.remove(id) > 0) {
			boolean b = FileUtil.deleteFile(fileName);
			if (!b) {
				return ResponseUtils.fail("数据库记录删除成功，文件删除失败");
			}
			return ResponseUtils.success();
		} else {
			return ResponseUtils.fail();
		}
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions(SecurityPermissionConstants.Common.COMMON_REMOVE)
	public BaseResponse<Void> remove(@RequestParam("ids[]") Long[] ids) {
		if ("test".equals(getUsername())) {
			return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
		}
		sysFileService.batchRemove(ids);
		return ResponseUtils.success();
	}

	@ResponseBody
	@PostMapping("/upload")
	BaseResponse<Void> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		if ("test".equals(getUsername())) {
			return ResponseUtils.fail(1, "演示系统不允许修改,完整体验请部署程序");
		}
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName);
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
		try {
			FileUtil.uploadFile(file.getBytes(), guiConfig.getUploadPath(), fileName);
		} catch (Exception e) {
			return ResponseUtils.fail();
		}

		if (sysFileService.save(sysFile) > 0) {
			return ResponseUtils.success(sysFile.getUrl());
		}
		return ResponseUtils.fail();
	}


}
