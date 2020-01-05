package com.gui.module.device.service.impl;

import com.gui.module.device.dao.AppVerisonDao;
import com.gui.module.device.domain.AppVerisonDO;
import com.gui.module.device.service.AppVerisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;





@Service
public class AppVerisonServiceImpl implements AppVerisonService {
	@Autowired
	private AppVerisonDao appVerisonDao;
	
	@Override
	public AppVerisonDO get(Long id){
		return appVerisonDao.get(id);
	}
	
	@Override
	public List<AppVerisonDO> list(Map<String, Object> map){
		return appVerisonDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return appVerisonDao.count(map);
	}
	
	@Override
	public int save(AppVerisonDO appVerison){
		return appVerisonDao.save(appVerison);
	}
	
	@Override
	public int update(AppVerisonDO appVerison){
		return appVerisonDao.update(appVerison);
	}
	
	@Override
	public int remove(Long id){
		return appVerisonDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return appVerisonDao.batchRemove(ids);
	}
	
}
