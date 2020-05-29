package com.gui.module.bus.service;

import com.gui.module.bus.domain.LineDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author peigui.huang
 * @email 1157688065@qq.com
 * @date 2020-05-29 21:30:13
 */
public interface LineService {
	
	LineDO get(Long id);
	
	List<LineDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LineDO line);
	
	int update(LineDO line);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
