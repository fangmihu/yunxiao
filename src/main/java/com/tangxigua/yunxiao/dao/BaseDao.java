package com.tangxigua.yunxiao.dao;
/*******************************************************
 * Copyright (C) 2018 iQIYI.COM - All Rights Reserved
 *
 * This file is part of qisheng.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * Created by fangmingfu <fangmingfu_sx@qiyi.com>
 * in 2018/7/3
 *******************************************************/
import com.tangxigua.yunxiao.dao.sqlProvidr.BaseSqlProvider;
import com.tangxigua.yunxiao.model.Category;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.io.Serializable;
import java.util.List;


public interface BaseDao<T>{

    //添加
    int save(T t);
    //删除
    int delete(T t);
    //更新
    int update(T t);
    //根据id查询
    T findOne(Serializable id);
    //查询所有
    List<T> findAll();


//    @SelectProvider(type = BaseSqlProvider.class, method = "getByName")
//    Category getByName(@Param("name") String name, Class entityClass);
//
//    @InsertProvider(type = BaseSqlProvider.class, method = "insert")
//    int insert(Category category);
}

