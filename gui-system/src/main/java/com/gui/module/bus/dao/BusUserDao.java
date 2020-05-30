package com.gui.module.bus.dao;

import com.gui.module.bus.domain.UserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author peigui.huang
 * @email 1157688065@qq.com
 * @date 2020-05-30 09:50:30
 */
@Mapper
public interface BusUserDao {

        UserDO get(Long id);

    List<UserDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(UserDO user);

    int update(UserDO user);

    int remove(Long id);

    int batchRemove(Long[] ids);
}
