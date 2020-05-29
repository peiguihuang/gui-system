package com.gui.module.bus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.gui.module.bus.dao.LineDao;
import com.gui.module.bus.domain.LineDO;
import com.gui.module.bus.service.LineService;



@Service
public class LineServiceImpl implements LineService {
	@Autowired
	private LineDao lineDao;
	
	@Override
	public LineDO get(Long id){
		return lineDao.get(id);
	}
	
	@Override
	public List<LineDO> list(Map<String, Object> map){
		return lineDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return lineDao.count(map);
	}
	
	@Override
	public int save(LineDO line){
		return lineDao.save(line);
	}
	
	@Override
	public int update(LineDO line){
		return lineDao.update(line);
	}
	
	@Override
	public int remove(Long id){
		return lineDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return lineDao.batchRemove(ids);
	}
	
}
