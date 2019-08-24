package com.songjh.learncore.common.openapi.model.base;

import com.songjh.learncore.common.openapi.utils.ToString;


/**
 * Created  by songjh on 2019-08-17 08:30.
 */
public class ResponseData extends ToString{

    private static final long serialVersionUID = 2453380491824821118L;

    boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
