package com.gui.module.bus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.gui.module.bus.dao.SubOrderDao;
import com.gui.module.bus.domain.SubOrderDO;
import com.gui.module.bus.service.SubOrderService;


@Service
public class SubOrderServiceImpl implements SubOrderService {
    @Autowired
    private SubOrderDao subOrderDao;

    @Override
    public SubOrderDO get(Long id) {
        return subOrderDao.get(id);
    }

    @Override
    public List<SubOrderDO> list(Map<String, Object> map) {
        return subOrderDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return subOrderDao.count(map);
    }

    @Override
    public int save(SubOrderDO subOrder) {
        return subOrderDao.save(subOrder);
    }

    @Override
    public int update(SubOrderDO subOrder) {
        return subOrderDao.update(subOrder);
    }

    @Override
    public int remove(Long id) {
        return subOrderDao.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return subOrderDao.batchRemove(ids);
    }

}
