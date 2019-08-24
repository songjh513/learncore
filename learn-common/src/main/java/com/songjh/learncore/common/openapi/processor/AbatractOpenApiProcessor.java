package com.songjh.learncore.common.openapi.processor;

import com.alibaba.fastjson.JSON;
import com.songjh.learncore.common.openapi.biz.DishOrderCreateProcessor;
import com.songjh.learncore.common.openapi.exception.BizException;
import com.songjh.learncore.common.openapi.model.base.BaseOpenApiRequest;
import com.songjh.learncore.common.openapi.model.base.BaseOpenApiResponse;
import com.songjh.learncore.common.openapi.model.base.InvokeContent;
import com.songjh.learncore.common.openapi.model.base.InvokeResult;
import com.songjh.learncore.common.openapi.utils.ResultFactory;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created  by songjh on 2019-08-17 07:18.
 */
public abstract class AbatractOpenApiProcessor<T extends BaseOpenApiRequest, Q extends BaseOpenApiResponse> implements processor {


    @Override
    public InvokeResult execute(InvokeContent invokeContent) {

        InvokeResult invokeResult;

        try {
            //获取参数
            T request = getRequest(invokeContent.getBizContent());

            //参数校验
            validateParams(request);

            //业务逻辑执行
            Q response = execute(request);

            //组装结果返回
            if (response.isSuccess()) {
                invokeResult = ResultFactory.createCommonSuccessResult(response);
            } else {
                invokeResult = ResultFactory.createCommonFailResult(response.getErrorCode(), response.getErrorMsg(), response);
            }

        } catch (BizException e) {
            invokeResult = ResultFactory.createCommonFailResult(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            invokeResult = ResultFactory.createCommonFailResult(e.getMessage());
        }
        return invokeResult;
    }

    /**
     * 参数校验
     *
     * @param request
     */
    protected abstract void validateParams(T request);

    /**
     * 执行业务逻辑
     *
     * @param request
     * @return
     */
    protected abstract Q execute(T request);


    /**
     * 参数组装
     *
     * @param bizContent
     * @return
     */
    private T getRequest(String bizContent) {
        Type requestType = getRequestType(this.getClass());

        T request = null;
        try {
            request = JSON.parseObject(bizContent, requestType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return request;
    }


    public static Type getRequestType(Class<?> clazz) {
        Type superClass = clazz.getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType) superClass).getActualTypeArguments();
        return actualTypeArguments[0];
    }


    public static void main(String[] args) {
        DishOrderCreateProcessor dishOrderCreateProcessor = new DishOrderCreateProcessor();
        Type superClass = dishOrderCreateProcessor.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType) superClass).getActualTypeArguments();

        for (Type actualTypeArgument : actualTypeArguments) {
            System.out.println(actualTypeArgument);

        }
    }


}
