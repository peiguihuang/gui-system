package com.gui.module.bus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.gui.module.bus.dao.TimeDao;
import com.gui.module.bus.domain.TimeDO;
import com.gui.module.bus.service.TimeService;


@Service
public class TimeServiceImpl implements TimeService {
    @Autowired
    private TimeDao timeDao;

    @Override
    public TimeDO get(Long id) {
        return timeDao.get(id);
    }

    @Override
    public List<TimeDO> list(Map<String, Object> map) {
        return timeDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return timeDao.count(map);
    }

    @Override
    public int save(TimeDO time) {
        return timeDao.save(time);
    }

    @Override
    public int update(TimeDO time) {
        return timeDao.update(time);
    }

    @Override
    public int remove(Long id) {
        return timeDao.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return timeDao.batchRemove(ids);
    }

}
