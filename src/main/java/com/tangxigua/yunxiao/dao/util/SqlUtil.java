package com.tangxigua.yunxiao.dao.util;

import com.google.common.base.CaseFormat;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

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
public class SqlUtil {

    public static String getInsertValues(Class c){
        Field[] declaredFields = c.getDeclaredFields();
        return Arrays.stream(declaredFields)
                        .filter(f -> !"id".equals(f.getName()))
                        .map(field -> "#{" + field.getName() + "}").collect(joining(", "));
    }

    public static String getInsertFields(Class c){
        Field[] declaredFields = c.getDeclaredFields();
        return Arrays.stream(declaredFields)
                .filter(f -> !"id".equals(f.getName()))
                .map(f -> CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, f.getName()))
                .collect(joining(", "));
    }

    public static String getSelectFields(Class c){
        Field[] declaredFields = c.getDeclaredFields();
        return Arrays.stream(declaredFields)
                .map(f -> CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, f.getName()))
                .collect(joining(", "));
    }

    public static String getUpdateValues(Class c){
        Field[] declaredFields = c.getDeclaredFields();
        return Arrays.stream(declaredFields)
                .filter(f -> !"id".equals(f.getName()))
                .map(field -> field.getName() + " = #{" + field.getName() + "}").collect(joining(", "));
    }

    public static String getColumName(String field){
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field);
    }
}
