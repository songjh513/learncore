package com.songjh.learncore.common.openapi.biz;

import com.songjh.learncore.common.openapi.processor.AbatractOpenApiProcessor;

import com.songjh.learncore.common.openapi.model.request.DishOrderRequest;
import com.songjh.learncore.common.openapi.model.response.DishOrderResponse;

/**
 * Created  by songjh on 2019-08-17 09:01.
 */
public class DishOrderCreateProcessor extends AbatractOpenApiProcessor<DishOrderRequest, DishOrderResponse> {


    @Override
    protected void validateParams(DishOrderRequest request) {

    }

    @Override
    protected DishOrderResponse execute(DishOrderRequest request) {
        System.out.println(request);
        DishOrderResponse dishOrderResponse = new DishOrderResponse();
        dishOrderResponse.setOrderId("12306");
        return dishOrderResponse;
    }
}
