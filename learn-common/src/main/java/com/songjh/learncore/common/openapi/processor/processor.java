package com.songjh.learncore.common.openapi.processor;

import com.songjh.learncore.common.openapi.model.base.InvokeContent;
import com.songjh.learncore.common.openapi.model.base.InvokeResult;

/**
 * Created  by songjh on 2019-08-17 07:19.
 */
public interface processor {

    InvokeResult execute(InvokeContent invoke);
}
