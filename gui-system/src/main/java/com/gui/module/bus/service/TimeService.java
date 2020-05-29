package com.gui.module.bus.service;

import com.gui.module.bus.domain.TimeDO;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author peigui.huang
 * @email 1157688065@qq.com
 * @date 2020-05-29 22:17:27
 */
public interface TimeService {

        TimeDO get(Long id);

    List<TimeDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(TimeDO time);

    int update(TimeDO time);

    int remove(Long id);

    int batchRemove(Long[] ids);
}
