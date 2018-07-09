/*******************************************************
 * Copyright (C) 2018 fangmihu - All Rights Reserved
 *
 * Created by fangmihu <github.com/fangmihu>
 * in 2018/7/7
 *******************************************************/
package com.tangxigua.yunxiao.service;

import com.tangxigua.yunxiao.model.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderItemService {

    List<OrderItem> listByKeyWord(String keyWord, Long userId);

    List<OrderItem> listByOrderId(Long orderId);

    int create(OrderItem orderItem);

    int delete(Long id);

    int update(OrderItem orderItem);

}
