package com.gui.module.device.service;


import com.gui.module.device.domain.AppDeviceDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author peigui.huang
 * @email 1992lcg@163.com
 * @date 2020-01-05 15:16:40
 */
public interface AppDeviceService {
	
	AppDeviceDO get(Long id);
	
	List<AppDeviceDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AppDeviceDO appDevice);
	
	int update(AppDeviceDO appDevice);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
