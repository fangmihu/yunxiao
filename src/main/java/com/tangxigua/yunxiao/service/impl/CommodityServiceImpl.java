/*******************************************************
 * Copyright (C) 2018 fangmihu - All Rights Reserved
 *
 * Created by fangmihu <github.com/fangmihu>
 * in 2018/7/7
 *******************************************************/
package com.tangxigua.yunxiao.service.impl;

import com.tangxigua.yunxiao.dao.CommodityDao;
import com.tangxigua.yunxiao.model.Commodity;
import com.tangxigua.yunxiao.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityDao commodityDao;

    @Override
    public int create(Commodity commodity) {
        return commodityDao.insert(commodity);
    }

    @Override
    public int update(Commodity commodity) {
        return 0;
    }

    @Override
    public int delete(Commodity commodity) {
        return 0;
    }

    @Override
    public List<Commodity> listAll() {
        return null;
    }

    @Override
    public Commodity getById(Long id) {
        return null;
    }

    @Override
    public List<Commodity> listByKeyWord(String keyWord) {
        return null;
    }
}
