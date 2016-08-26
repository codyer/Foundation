package com.chinaredstar.foundation.common;

import com.chinaredstar.foundation.FoundationApplication;

/**
 * Created by cody.yi on 2016/7/13.
 *
 */
public class Constant {
    public final static String PKG_NAME = FoundationApplication.getContext().getPackageName();
    public static final boolean DEBUG = true;

    public static final String APP_ID = "";
    public static final String APP_SECRET = "";

    /**
     * 运行模式 DEBUG = 1; INFO = 2; WARN = 3; ERROR = 4; 优先级DEBUG < INFO < WARN <
     * ERROR
     * RUNNING_MODE大于4时Log关闭
     */
    public static final int RUNNING_MODE = 1;

    /**
     * 请求地址相关
     */
    public interface HttpUrl {
        String SERVER = "http://192.168.226.50";
        String BASE_URL = "api.uc.chinaredstar.dev";//开发环境
//        String BASE_URL = "api.uc.hxmklmall.cn";//测试环境
//        String BASE_URL = "api.uc.chinaredstar.pro";//正式环境

        String URL_CHECK_MOBILE_EXIST = BASE_URL + "/partner/isMobileExist";
        String URL_GET_TOKEN = BASE_URL + "/partner/getTokenByClient";

        String PORT = ":8080";
        String PREFIX = "/order_api";
        String API_MERCHANT_LOGIN = "/merchantApi/merchantLogin";
        String API_MERCHANT_INFO = "/order_api/merchantInfo";
        String API_TEST = "/test";
        String API_TEST_LIST = "/test_list";
        String API_TEST_UPLOAD = "/test_upload";
        String API_TEST_BASE64 = "/test_base64";
    }

    public interface HttpCode {
        String SUCCESS = "1000";//成功
        String EMPTY_PARAM = "1001";//参数为空
        String INVALID_MEMBER = "1002";//会员参数错误
        String QUERY_EMPTY = "1003";//查询结果为空
    }
}
