package com.gui.module.bus.dao;

import com.gui.module.bus.domain.LineDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author peigui.huang
 * @email 1157688065@qq.com
 * @date 2020-05-29 21:30:13
 */
@Mapper
public interface LineDao {

	LineDO get(Long id);
	
	List<LineDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LineDO line);
	
	int update(LineDO line);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
