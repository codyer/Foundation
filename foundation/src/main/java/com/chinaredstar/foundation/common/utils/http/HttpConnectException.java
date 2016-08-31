package com.chinaredstar.foundation.common.utils.http;

/**
 * Created by cody.yi on 2016/7/21.
 * http请求异常
 */
public class HttpConnectException extends RuntimeException {
    private String retCd ;  //异常对应的返回码
    private String msgDes;  //异常对应的描述信息

    public HttpConnectException() {
        super();
    }

    public HttpConnectException(String message) {
        super(message);
        msgDes = message;
    }

    public HttpConnectException(String retCd, String msgDes) {
        super();
        this.retCd = retCd;
        this.msgDes = msgDes;
    }

    public String getRetCd() {
        return retCd;
    }

    public String getMsgDes() {
        return msgDes;
    }
}
