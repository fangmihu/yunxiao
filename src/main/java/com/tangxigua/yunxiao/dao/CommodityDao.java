package com.tangxigua.yunxiao.dao;

import com.tangxigua.yunxiao.dao.sqlProvidr.CommoditySqlProvider;
import com.tangxigua.yunxiao.model.Commodity;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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


@Mapper
@Repository
public interface CommodityDao {

    String TABLE_NAME = "commodity";
    String INSERT_FIELDS = " category_id, name, price, stock ";
    String INSERT_VALUES = " #{categoryId}, #{name}, #{price}, #{stock} ";
    String SELECT_FIELDS = " id,categoryId, name, price, stock ";

    @InsertProvider(type = CommoditySqlProvider.class, method = "insert")
    int insert(Commodity commodity);


}
