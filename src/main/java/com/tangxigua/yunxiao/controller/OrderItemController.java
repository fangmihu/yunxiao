/*******************************************************
 * Copyright (C) 2018 fangmihu - All Rights Reserved
 *
 * Created by fangmihu <github.com/fangmihu>
 * in 2018/7/7
 *******************************************************/
package com.tangxigua.yunxiao.controller;

import com.tangxigua.yunxiao.VO.ViewObject;
import com.tangxigua.yunxiao.model.OrderItem;
import com.tangxigua.yunxiao.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/orderItem")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping("/create")
    public ViewObject create(OrderItem orderItem){
        ViewObject vo = new ViewObject();
        if(orderItemService.create(orderItem) == 0){
            vo.add("msg", "添加失败");
        }
        return vo;
    }
}
