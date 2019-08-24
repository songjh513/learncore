package com.songjh.learncore.common.openapi.model.response;

import com.songjh.learncore.common.openapi.model.base.BaseOpenApiResponse;

/**
 * Created  by songjh on 2019-08-17 09:03.
 */
public class DishOrderResponse extends BaseOpenApiResponse {

    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
