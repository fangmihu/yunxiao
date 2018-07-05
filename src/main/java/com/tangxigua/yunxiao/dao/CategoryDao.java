package com.tangxigua.yunxiao.dao;

import com.tangxigua.yunxiao.dao.sqlProvidr.CategorySqlProvider;
import com.tangxigua.yunxiao.model.Category;
import org.apache.ibatis.annotations.*;
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
public interface CategoryDao {
    String TABLE_NAME = "category";
    String SELECT_FIELDS = " id, name, description ";

    @SelectProvider(type = CategorySqlProvider.class, method = "getByName")
    Category getByName(@Param("name") String name);

    @InsertProvider(type = CategorySqlProvider.class, method = "save")
    int save(Category category);
}
