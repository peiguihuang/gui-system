package com.gui.module.bus.service.impl;

import com.gui.module.bus.dao.BusUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.gui.module.bus.domain.UserDO;
import com.gui.module.bus.service.BusUserService;


@Service
public class BusUserServiceImpl implements BusUserService {
    @Autowired
    private BusUserDao userDao;

    @Override
    public UserDO get(Long id) {
        return userDao.get(id);
    }

    @Override
    public List<UserDO> list(Map<String, Object> map) {
        return userDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return userDao.count(map);
    }

    @Override
    public int save(UserDO user) {
        return userDao.save(user);
    }

    @Override
    public int update(UserDO user) {
        return userDao.update(user);
    }

    @Override
    public int remove(Long id) {
        return userDao.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return userDao.batchRemove(ids);
    }

}
