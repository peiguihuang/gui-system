package com.gui.module.device.service;


import com.gui.module.device.domain.AppVerisonDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author peigui.huang
 * @email 1992lcg@163.com
 * @date 2020-01-05 15:16:43
 */
public interface AppVerisonService {
	
	AppVerisonDO get(Long id);
	
	List<AppVerisonDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AppVerisonDO appVerison);
	
	int update(AppVerisonDO appVerison);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
