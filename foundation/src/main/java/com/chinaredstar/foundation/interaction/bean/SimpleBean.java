/*
 * Copyright (c)  Created by Cody.yi on 2016/9/6.
 */

package com.chinaredstar.foundation.interaction.bean;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cody.yi on 2016/7/12.
 * 网络只返回简单的code和message的bean
 */
public class SimpleBean {
    /**
     * String	操作状态码
     */
    private String code;

    /**
     * String	操作提示信息
     */
    private String message;

    /**
     * data	返回数据
     * 相当于data model
     */
    @SerializedName(value = "data", alternate = {"dataMap", "dataReturn"})
    private Object data;

    public SimpleBean(Result result) {
        this.code = result.getCode();
        this.message = result.getMessage();
        this.data = result.getData();
    }

    public SimpleBean(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        if (code == null){
            return "-1";
        }
        return code;
    }

    public String getMessage() {
        if (message == null){
            return "no message!";
        }
        return message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SimpleBean{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public interface Code{
        String UNKNOWN_REASON = "40000";//其他未知原因
        String REQUEST_ERROR = "40001";//请求出错
        String NETWORK_DISCONNECTED = "40002";//网络无连接
        String PARAMETER_ERROR = "40003";//参数错误
    }
}
