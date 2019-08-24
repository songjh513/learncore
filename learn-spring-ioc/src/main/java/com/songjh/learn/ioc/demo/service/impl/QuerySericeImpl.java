package com.songjh.learn.ioc.demo.service.impl;

import com.songjh.learn.ioc.demo.service.IQueryService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created  by songjh on 2019-04-11 07:43.
 */
public class QuerySericeImpl  implements IQueryService {

    /**
     * 查询
     */
    public String query(String name) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        String json = "{name:\"" + name + "\",time:\"" + time + "\"}";
        return json;
    }
}
