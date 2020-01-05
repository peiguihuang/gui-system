package com.gui.module.device.service.impl;

import com.gui.module.device.dao.MerchantDeviceDao;
import com.gui.module.device.domain.MerchantDeviceDO;
import com.gui.module.device.service.MerchantDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;





@Service
public class MerchantDeviceServiceImpl implements MerchantDeviceService {
	@Autowired
	private MerchantDeviceDao merchantDeviceDao;
	
	@Override
	public MerchantDeviceDO get(Long id){
		return merchantDeviceDao.get(id);
	}
	
	@Override
	public List<MerchantDeviceDO> list(Map<String, Object> map){
		return merchantDeviceDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return merchantDeviceDao.count(map);
	}
	
	@Override
	public int save(MerchantDeviceDO merchantDevice){
		return merchantDeviceDao.save(merchantDevice);
	}
	
	@Override
	public int update(MerchantDeviceDO merchantDevice){
		return merchantDeviceDao.update(merchantDevice);
	}
	
	@Override
	public int remove(Long id){
		return merchantDeviceDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return merchantDeviceDao.batchRemove(ids);
	}
	
}
