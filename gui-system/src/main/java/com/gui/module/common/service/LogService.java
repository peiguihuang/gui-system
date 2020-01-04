package com.gui.module.common.service;

import com.gui.module.common.domain.LogDO;
import com.gui.module.common.domain.PageDO;
import com.gui.module.common.utils.Query;
import org.springframework.stereotype.Service;

@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
