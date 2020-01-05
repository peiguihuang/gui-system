package com.gui.module.device.service;


import com.gui.module.device.domain.MerchantDeviceDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author peigui.huang
 * @email 1992lcg@163.com
 * @date 2020-01-05 15:16:46
 */
public interface MerchantDeviceService {
	
	MerchantDeviceDO get(Long id);
	
	List<MerchantDeviceDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MerchantDeviceDO merchantDevice);
	
	int update(MerchantDeviceDO merchantDevice);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
