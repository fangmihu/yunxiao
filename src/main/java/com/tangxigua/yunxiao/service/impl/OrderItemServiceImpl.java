/*******************************************************
 * Copyright (C) 2018 fangmihu - All Rights Reserved
 *
 * Created by fangmihu <github.com/fangmihu>
 * in 2018/7/7
 *******************************************************/
package com.tangxigua.yunxiao.service.impl;

import com.tangxigua.yunxiao.dao.OrderItemDao;
import com.tangxigua.yunxiao.model.OrderItem;
import com.tangxigua.yunxiao.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemDao orderItemDao;

    @Override
    public List<OrderItem> listByOrderId(Long orderId) {
        return null;
    }

    @Override
    public int create(OrderItem orderItem) {
        return orderItemDao.insert(orderItem);
    }

    @Override
    public List<OrderItem> listByKeyWord(String keyWord, Long userId) {
        return null;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int update(OrderItem orderItem) {
        return 0;
    }
}
