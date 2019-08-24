package com.songjh.learncore.common.openapi.model.base;

import com.songjh.learncore.common.openapi.utils.ToString;

/**
 * Created  by songjh on 2019-08-17 07:21.
 */
public class BaseOpenApiRequest extends ToString {

    private String appId;

    private String pid;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
