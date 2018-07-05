package com.tangxigua.yunxiao.service;

import com.tangxigua.yunxiao.model.User;
import com.tangxigua.yunxiao.model.ViewObject;

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


public interface UserService {

    User getByUserName(String UserName);

    ViewObject login(String userName, String password);

    ViewObject register(String userName, String password);
}
