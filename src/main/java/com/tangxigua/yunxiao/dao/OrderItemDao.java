/*******************************************************
 * Copyright (C) 2018 fangmihu - All Rights Reserved
 *
 * Created by fangmihu <github.com/fangmihu>
 * in 2018/7/7
 *******************************************************/
package com.tangxigua.yunxiao.dao;

import com.tangxigua.yunxiao.dao.sqlProvidr.OrderItemSqlProvider;
import com.tangxigua.yunxiao.model.OrderItem;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface OrderItemDao {

    String TABLE_NAME = "order_item";
    String SELECT_FIELDS = "order_id, commodity_id, price, sum";
    String INSERT_FIELDS = "orderId, commodityId, price, sum";
    String INSERT_VALUES = "#{orderId}, #{commodityId}, #{price}, #{sum}";

    @Select({"select ", SELECT_FIELDS, "from ", TABLE_NAME, "where order_id = #{orderId}"})
    List<OrderItem> listByOrderId(@Param("orderId") Long orderId);

    @InsertProvider(type = OrderItemSqlProvider.class, method = "insert")
    int insert(OrderItem orderItem);
}
