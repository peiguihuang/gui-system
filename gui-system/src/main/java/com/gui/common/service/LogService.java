package com.gui.common.service;

import org.springframework.stereotype.Service;

import com.gui.common.domain.LogDO;
import com.gui.common.domain.PageDO;
import com.gui.common.utils.Query;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
