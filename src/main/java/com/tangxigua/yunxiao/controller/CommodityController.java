/*******************************************************
 * Copyright (C) 2018 fangmihu - All Rights Reserved
 *
 * Created by fangmihu <github.com/fangmihu>
 * in 2018/7/7
 *******************************************************/
package com.tangxigua.yunxiao.controller;

import com.tangxigua.yunxiao.VO.ViewObject;
import com.tangxigua.yunxiao.model.Commodity;
import com.tangxigua.yunxiao.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @PostMapping("/create")
    public ViewObject create(@Valid Commodity commodity){
        ViewObject vo = new ViewObject();
        if(commodityService.create(commodity) == 0) {
            vo.add("msg", "创建失败");
        }
        return vo;
    }

    @GetMapping("/buy")
    public void buy(){

    }

    @GetMapping("/search")
    public void search(@RequestParam("searchWord") String searchWord){

    }

    @GetMapping("/listDefault")
    public List<Commodity> listDefault(){
        Commodity commodity = new Commodity();
        commodity.setId(1L);
        commodity.setName("test");
        commodity.setPicUrl("http://images.nowcoder.com/head/151t.png");

        Commodity commodity1 = new Commodity();
        commodity1.setId(2L);
        commodity1.setName("test1");
        commodity1.setPicUrl("http://images.nowcoder.com/head/850t.png");

        Commodity commodity2 = new Commodity();
        commodity2.setId(3L);
        commodity2.setName("test2");
        commodity2.setPicUrl("http://images.nowcoder.com/head/855t.png");

        ArrayList list = new ArrayList();
        list.add(commodity);
        list.add(commodity1);
        list.add(commodity2);
        return list;
//        return commodityService.listAll();
    }

}
