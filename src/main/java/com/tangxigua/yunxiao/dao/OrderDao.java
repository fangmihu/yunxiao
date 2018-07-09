/*******************************************************
 * Copyright (C) 2018 fangmihu - All Rights Reserved
 *
 * Created by fangmihu <github.com/fangmihu>
 * in 2018/7/7
 *******************************************************/
package com.tangxigua.yunxiao.dao;

import com.tangxigua.yunxiao.dao.sqlProvidr.OrderSqlProvider;
import com.tangxigua.yunxiao.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface OrderDao {

    String TABLE_NAME = "order";
    String INSERT_FIELDS = "customer_id, phone_number, Address, total_price, create_date";
    String INSERT_VLUES = "#{customerId}, #{phoneNumber}, #{Address}, #{totalPrice}, #{createDate}";
    String SELECT_FIELDS = "id, customerId, phoneNumber, Address, totalPrice, createDate";

    @SelectProvider(type = OrderSqlProvider.class, method = "insert")
    int insert(Order order);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, "where user_id = #{userId}"})
    List<Order> listByUserId(@Param("userId") Long userId);
}
