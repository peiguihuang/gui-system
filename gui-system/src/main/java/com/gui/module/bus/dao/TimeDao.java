package com.gui.module.bus.dao;

import com.gui.module.bus.domain.TimeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author peigui.huang
 * @email 1157688065@qq.com
 * @date 2020-05-29 22:17:27
 */
@Mapper
public interface TimeDao {

    TimeDO get(Long id);

    List<TimeDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(TimeDO time);

    int update(TimeDO time);

    int remove(Long id);

    int batchRemove(Long[] ids);
}
