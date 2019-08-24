package com.songjh.learncore.common.openapi.model.base;

import com.songjh.learncore.common.openapi.utils.ToString;

/**
 * Created  by songjh on 2019-08-17 07:20.
 */
public class InvokeResult extends ToString {

    private static final long serialVersionUID = -7307909493083879473L;

    private boolean success = false;

    private Object response;

    private String errorCode;

    private String errorMsg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

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
