package com.gui.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.gui.oa.dao.NotifyRecordDao;
import com.gui.oa.domain.NotifyRecordDO;
import com.gui.oa.service.NotifyRecordService;



@Service
public class NotifyRecordServiceImpl implements NotifyRecordService {
	@Autowired
	private NotifyRecordDao notifyRecordDao;
	
	@Override
	public NotifyRecordDO get(Long id){
		return notifyRecordDao.get(id);
	}
	
	@Override
	public List<NotifyRecordDO> list(Map<String, Object> map){
		return notifyRecordDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return notifyRecordDao.count(map);
	}
	
	@Override
	public int save(NotifyRecordDO notifyRecord){
		return notifyRecordDao.save(notifyRecord);
	}
	
	@Override
	public int update(NotifyRecordDO notifyRecord){
		return notifyRecordDao.update(notifyRecord);
	}
	
	@Override
	public int remove(Long id){
		return notifyRecordDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return notifyRecordDao.batchRemove(ids);
	}

	@Override
	public int changeRead(NotifyRecordDO notifyRecord) {
		return notifyRecordDao.changeRead(notifyRecord);
	}

}
