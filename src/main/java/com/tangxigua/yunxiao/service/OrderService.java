/*******************************************************
 * Copyright (C) 2018 fangmihu - All Rights Reserved
 *
 * Created by fangmihu <github.com/fangmihu>
 * in 2018/7/7
 *******************************************************/
package com.tangxigua.yunxiao.service;

import com.tangxigua.yunxiao.model.OrderItem;
import com.tangxigua.yunxiao.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> listByUserId(Long userId, Integer limit);

    int createOrder(Order order);

    /**
     *
     * @param orderId
     * @return 支付结果
     */
    boolean pay(Long orderId);

}
