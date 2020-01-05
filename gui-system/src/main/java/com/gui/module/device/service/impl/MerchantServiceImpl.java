package com.gui.module.device.service.impl;

import com.gui.module.device.dao.MerchantDao;
import com.gui.module.device.domain.MerchantDO;
import com.gui.module.device.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;





@Service
public class MerchantServiceImpl implements MerchantService {
	@Autowired
	private MerchantDao merchantDao;
	
	@Override
	public MerchantDO get(Long id){
		return merchantDao.get(id);
	}
	
	@Override
	public List<MerchantDO> list(Map<String, Object> map){
		return merchantDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return merchantDao.count(map);
	}
	
	@Override
	public int save(MerchantDO merchant){
		return merchantDao.save(merchant);
	}
	
	@Override
	public int update(MerchantDO merchant){
		return merchantDao.update(merchant);
	}
	
	@Override
	public int remove(Long id){
		return merchantDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return merchantDao.batchRemove(ids);
	}
	
}
