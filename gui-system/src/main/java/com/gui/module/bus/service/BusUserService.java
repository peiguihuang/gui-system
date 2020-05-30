package com.gui.module.bus.service;

import com.gui.module.bus.domain.UserDO;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author peigui.huang
 * @email 1157688065@qq.com
 * @date 2020-05-30 09:50:30
 */
public interface BusUserService {

        UserDO get(Long id);

    List<UserDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(UserDO user);

    int update(UserDO user);

    int remove(Long id);

    int batchRemove(Long[] ids);
}
