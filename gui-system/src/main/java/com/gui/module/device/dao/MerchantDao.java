package com.gui.module.device.dao;


import com.gui.module.device.domain.MerchantDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author peigui.huang
 * @email 1992lcg@163.com
 * @date 2020-01-05 15:16:44
 */
@Mapper
public interface MerchantDao {

	MerchantDO get(Long id);
	
	List<MerchantDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MerchantDO merchant);
	
	int update(MerchantDO merchant);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
