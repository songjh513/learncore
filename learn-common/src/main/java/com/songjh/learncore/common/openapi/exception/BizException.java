package com.songjh.learncore.common.openapi.exception;

/**
 * Created  by songjh on 2019-08-17 08:22.
 */
public class BizException extends RuntimeException{

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
