/*******************************************************
 * Copyright (C) 2018 fangmihu - All Rights Reserved
 *
 * Created by fangmihu <github.com/fangmihu>
 * in 2018/7/7
 *******************************************************/
package com.tangxigua.yunxiao.controller;


import com.tangxigua.yunxiao.VO.ViewObject;
import com.tangxigua.yunxiao.model.Order;
import com.tangxigua.yunxiao.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ViewObject create (Order order){
        ViewObject vo = new ViewObject();
        if(orderService.createOrder( order) == 0){
            vo.add("msg","创建订单失败");
        }
        return vo;
    }

}
