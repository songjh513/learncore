package com.songjh.learncore.common.openapi.utils;

import com.songjh.learncore.common.openapi.model.base.InvokeResult;

/**
 * Created  by songjh on 2019-08-17 08:23.
 */
public class ResultFactory {


    /**
     * 构造返回成功的结果
     * @param response
     * @return
     */
    public static  InvokeResult createCommonSuccessResult(Object response) {

        InvokeResult invokeResult = new InvokeResult();
        invokeResult.setSuccess(true);
        invokeResult.setResponse(response);
        return invokeResult;
    }

    /**
     * 构造返回失败的结果
     * @param errorCode
     * @param errorMsg
     * @param response
     * @return
     */
    public static InvokeResult createCommonFailResult(String errorCode, String errorMsg, Object response) {
        InvokeResult invokeResult = new InvokeResult();
        invokeResult.setErrorCode(errorCode);
        invokeResult.setErrorMsg(errorMsg);
        invokeResult.setResponse(response);
        return invokeResult;
    }


    /**
     * 构造返回失败的结果
     * @param errorMsg
     * @return
     */
    public static InvokeResult createCommonFailResult(String errorMsg) {
        InvokeResult invokeResult = new InvokeResult();
        invokeResult.setErrorCode("10001");
        invokeResult.setErrorMsg(errorMsg);
        return invokeResult;
    }

    /**
     * 构造返回失败的结果
     * @param errorCode
     * @param errorMsg
     * @return
     */
    public static InvokeResult createCommonFailResult(String errorCode, String errorMsg) {
        InvokeResult invokeResult = new InvokeResult();
        invokeResult.setErrorCode(errorCode);
        invokeResult.setErrorMsg(errorMsg);
        return invokeResult;
    }
}
