package com.tangxigua.yunxiao.dao;

import com.tangxigua.yunxiao.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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

@Mapper
@Repository
public interface UserDao {

    String TABLE_NAME = "user";
    String SELECT_FIELDS = " id, username, password, salt ";
    String INSERT_FIELDS = "( username, password, salt )";
    String INSERT_VALUES = " #{userName}, #{password}, #{salt} ";

    @Select({"select " , SELECT_FIELDS, " from ", TABLE_NAME, "where user_name = #{userName}"})
    User getByUserName(@Param("userName") String userName);

    @Insert({"insert into ", TABLE_NAME, INSERT_FIELDS, " values (" + INSERT_VALUES + ")"})
    int insert(User user);

    @Select({"select id from user where id = 100"})
    Long test();

}
