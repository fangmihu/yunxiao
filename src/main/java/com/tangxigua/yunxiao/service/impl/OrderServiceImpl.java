/*******************************************************
 * Copyright (C) 2018 fangmihu - All Rights Reserved
 *
 * Created by fangmihu <github.com/fangmihu>
 * in 2018/7/7
 *******************************************************/
package com.tangxigua.yunxiao.service.impl;

import com.tangxigua.yunxiao.dao.OrderDao;
import com.tangxigua.yunxiao.model.Order;
import com.tangxigua.yunxiao.model.OrderItem;
import com.tangxigua.yunxiao.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> listByUserId(Long userId, Integer limit) {
        return null;
    }

    @Override
    public int createOrder(Order order) {
        return orderDao.insert(order);
    }

    @Override
    public boolean pay(Long orderId) {
        return false;
    }
}
