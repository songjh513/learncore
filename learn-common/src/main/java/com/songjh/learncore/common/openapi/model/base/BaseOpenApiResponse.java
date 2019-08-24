package com.songjh.learncore.common.openapi.model.base;

/**
 * Created  by songjh on 2019-08-17 07:21.
 */
public class BaseOpenApiResponse extends ResponseData {


    String errorCode;

    String errorMsg;


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
