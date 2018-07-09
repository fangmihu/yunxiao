package com.tangxigua.yunxiao.VO;

import java.util.HashMap;
import java.util.Map;

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


public class ViewObject {

    private Map viewMap ;

    public ViewObject(){
        viewMap = new HashMap();
    }

    public void add(Object key, Object value){
        viewMap.put(key, value);
    }

    public boolean isEmpty(){
        return viewMap.isEmpty();
    }

}
