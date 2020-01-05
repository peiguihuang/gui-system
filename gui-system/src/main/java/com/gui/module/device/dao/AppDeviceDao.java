package com.gui.module.device.dao;

import com.gui.module.device.domain.AppDeviceDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author peigui.huang
 * @email 1992lcg@163.com
 * @date 2020-01-05 15:16:40
 */
@Mapper
public interface AppDeviceDao {

	AppDeviceDO get(Long id);
	
	List<AppDeviceDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AppDeviceDO appDevice);
	
	int update(AppDeviceDO appDevice);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
