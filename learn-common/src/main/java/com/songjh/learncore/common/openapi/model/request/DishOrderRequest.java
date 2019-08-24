package com.songjh.learncore.common.openapi.model.request;

import com.songjh.learncore.common.openapi.model.base.BaseOpenApiRequest;

/**
 * Created  by songjh on 2019-08-17 09:03.
 */
public class DishOrderRequest extends BaseOpenApiRequest {

    private String shopId;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DishOrderRequest{");
        sb.append("shopId='").append(shopId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
