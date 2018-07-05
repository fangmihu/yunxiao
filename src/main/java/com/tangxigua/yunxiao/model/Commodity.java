package com.tangxigua.yunxiao.model;

import com.tangxigua.yunxiao.dao.util.TableName;

/*******************************************************
 * Copyright (C) 2018 iQIYI.COM - All Rights Reserved
 *
 * This file is part of qisheng.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * Created by fangmingfu_sx <fangmingfu_sx@qiyi.com>
 * in 2018/7/4
 *******************************************************/

@TableName("commodity")
public class Commodity {

    private Long id;

    private Long categoryId;

    private String name;

    private Double price;

    private Long stock;

}
