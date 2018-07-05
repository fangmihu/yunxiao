package com.tangxigua.yunxiao.service;

import com.tangxigua.yunxiao.dao.UserDao;
import com.tangxigua.yunxiao.model.User;
import com.tangxigua.yunxiao.model.ViewObject;
import com.tangxigua.yunxiao.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.security.provider.MD5;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public User getByUserName(String userName){
        return userDao.getByUserName(userName);
    }

    @Override
    public ViewObject login(String userName, String password) {

        ViewObject res = new ViewObject();
        User user = getByUserName(userName);
        if(user == null){
            res.add("msg", "用户名不存在");
        }else{
            if(user.getPassword().equals(CommonUtil.encodeByMD5(user.getSalt() + password))){
                res.add("msg", "登录成功");
            }else{
                res.add("msg","密码错误");
            }
        }
        return res;
    }


    @Override
    public ViewObject register(String userName, String password) {
        ViewObject res = new ViewObject();
        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
            res.add("msg","用户名和密码不能为空");
            return res;
        }
        User user = new User();
        user.setUserName(userName);
        user.setSalt(UUID.randomUUID().toString());
        user.setPassword(CommonUtil.encodeByMD5(user.getSalt() + password));
        userDao.insert(user);
        res.add("msg","恭喜! 注册成功!");
        return res;
    }

}
