package com.songjh.learncore.common.openapi.test;

import com.alibaba.fastjson.JSON;
import com.songjh.learncore.common.openapi.biz.DishOrderCreateProcessor;
import com.songjh.learncore.common.openapi.model.base.InvokeContent;
import com.songjh.learncore.common.openapi.model.base.InvokeResult;
import com.songjh.learncore.common.openapi.model.request.DishOrderRequest;
import org.json.JSONObject;

/**
 * Created  by songjh on 2019-08-17 09:48.
 */
public class OpenApiDemo {

    public static void main(String[] args) {

        DishOrderCreateProcessor dishOrderCreateProcessor = new DishOrderCreateProcessor();

        InvokeContent invokeContent = new InvokeContent();

        invokeContent.setBizContent("{\"appId\":\"222\",\"pid\":\"3333\",\"shopId\":\"1111\"}");
        InvokeResult invokeResult = dishOrderCreateProcessor.execute(invokeContent);

        System.out.println(invokeResult);

//        DishOrderRequest dishOrderRequest = new DishOrderRequest();
//        dishOrderRequest.setShopId("1111");
//        dishOrderRequest.setAppId("222");
//        dishOrderRequest.setPid("3333");
//
//        System.out.println(JSON.toJSON(dishOrderRequest));
    }
}
