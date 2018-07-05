package com.tangxigua.yunxiao.dao.sqlProvidr;

import com.mysql.cj.xdevapi.Table;
import com.tangxigua.yunxiao.dao.util.SqlUtil;
import com.tangxigua.yunxiao.dao.util.TableName;
import jdk.nashorn.internal.objects.annotations.Where;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

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


public class BaseSqlProvider<T> {

    private Class clazz;

    private String TABLE_NAME;

    private String SELECT_FIELDS;

    private String INSERT_FIELDS;

    private String INSERT_VALUES;

    private String UPDATE_FIELDS;

    public BaseSqlProvider(){
        //目的：得到实际类型参数
        //得到当前运行对象
        Class clazz = this.getClass();
        //得到当前对象父类的参数化类型,一般使用type子接口ParameterizedType
        Type type = clazz.getGenericSuperclass();
        ParameterizedType ptype = (ParameterizedType)type;
        //得到实际类型参数
        Type[] types = ptype.getActualTypeArguments();
        Class clazzParameter = (Class)types[0];
        this.clazz = clazzParameter;
        TableName tableName  = (TableName) clazzParameter.getAnnotation(TableName.class);
        this.TABLE_NAME = tableName.value();
        this.INSERT_FIELDS = SqlUtil.getInsertFields(clazzParameter);
        this.INSERT_VALUES = SqlUtil.getInsertValues(clazzParameter);
        this.SELECT_FIELDS = SqlUtil.getSelectFields(clazzParameter);
        this.UPDATE_FIELDS = SqlUtil.getUpdateValues(clazzParameter);
    }

    //添加
    public String save(T t){
        SQL sql = new SQL();
        sql.INSERT_INTO(TABLE_NAME).VALUES(INSERT_FIELDS, INSERT_VALUES);
        return sql.toString();
    }

    //根据name查询
    public String getByName(@Param("name") String name){
        SQL sql = new SQL();
        sql.SELECT(SELECT_FIELDS).FROM(TABLE_NAME).WHERE("name = #{name}");
        return sql.toString();
    }

    //删除
    public String deleteById(@Param("id") Long id){
       SQL sql = new SQL();
       sql.DELETE_FROM(TABLE_NAME).WHERE("id = #{id}");
       return sql.toString();
    }

    //更新
    public String update(T t, @Param("id") Long id){
        SQL sql = new SQL();
        sql.UPDATE(TABLE_NAME).SET(UPDATE_FIELDS).WHERE("id = #{id}");
        return sql.toString();
    }

    //查询所有
    public String findAll(){
        SQL sql = new SQL();
        sql.SET(SELECT_FIELDS).FROM(TABLE_NAME);
        return sql.toString();
    }


}
