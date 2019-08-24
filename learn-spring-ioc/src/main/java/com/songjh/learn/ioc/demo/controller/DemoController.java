package com.songjh.learn.ioc.demo.controller;

import com.songjh.learn.ioc.annotation.Autowired;
import com.songjh.learn.ioc.annotation.Controller;
import com.songjh.learn.ioc.annotation.RequestMapping;
import com.songjh.learn.ioc.annotation.RequestParam;
import com.songjh.learn.ioc.demo.service.IQueryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created  by songjh on 2019-04-11 07:42.
 */
@Controller
public class DemoController {

    @Autowired
   private  IQueryService queryService;

    @RequestMapping("/query.json")
    public Object query(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam("name") String name){
        String result = queryService.query(name);
        System.out.println(result);
        return out(response,result);
    }


    private Object out(HttpServletResponse resp,String str){
        try {
            resp.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
