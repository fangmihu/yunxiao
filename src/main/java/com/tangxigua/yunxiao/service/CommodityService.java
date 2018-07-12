/*******************************************************
 * Copyright (C) 2018 fangmihu - All Rights Reserved
 *
 * Created by fangmihu <github.com/fangmihu>
 * in 2018/7/7
 *******************************************************/
package com.tangxigua.yunxiao.service;

import com.tangxigua.yunxiao.model.Commodity;

import java.util.List;

public interface CommodityService {

    int create(Commodity commodity);

    int update(Commodity commodity);

    int delete(Commodity commodity);

    List<Commodity> listAll();

    Commodity getById(Long id);

    List<Commodity> listByKeyWord(String keyWord);

    List<Commodity> searchByKeyWord(String keyWord);
}
