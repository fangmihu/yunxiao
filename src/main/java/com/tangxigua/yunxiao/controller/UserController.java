/*******************************************************
 * Copyright (C) 2018 iQIYI.COM - All Rights Reserved
 *
 * This file is part of qisheng.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * Created by fangmingfu_sx <fangmingfu_sx@qiyi.com>
 * in 2018/7/3
 *******************************************************/
package com.tangxigua.yunxiao.controller;

import com.tangxigua.yunxiao.model.User;
import com.tangxigua.yunxiao.VO.ViewObject;
import com.tangxigua.yunxiao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ViewObject register(@RequestBody User user){
        return userService.register(user.getUserName(), user.getPassword());
    }

    @PostMapping("/login")
    public ViewObject login(@RequestBody User user){
        return userService.login( user.getUserName(), user.getPassword());
    }
}
