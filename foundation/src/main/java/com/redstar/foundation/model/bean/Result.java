package com.redstar.foundation.model.bean;

/**
 * Created by cody.yi on 2016/7/12.
 */
public class Result<T> {
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
     */
    private T data;

    public Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
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

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
