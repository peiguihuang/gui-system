package com.gui.module.device.service.impl;

import com.gui.module.device.dao.AppDeviceDao;
import com.gui.module.device.domain.AppDeviceDO;
import com.gui.module.device.service.AppDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;





@Service
public class AppDeviceServiceImpl implements AppDeviceService {
	@Autowired
	private AppDeviceDao appDeviceDao;
	
	@Override
	public AppDeviceDO get(Long id){
		return appDeviceDao.get(id);
	}
	
	@Override
	public List<AppDeviceDO> list(Map<String, Object> map){
		return appDeviceDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return appDeviceDao.count(map);
	}
	
	@Override
	public int save(AppDeviceDO appDevice){
		return appDeviceDao.save(appDevice);
	}
	
	@Override
	public int update(AppDeviceDO appDevice){
		return appDeviceDao.update(appDevice);
	}
	
	@Override
	public int remove(Long id){
		return appDeviceDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return appDeviceDao.batchRemove(ids);
	}
	
}
