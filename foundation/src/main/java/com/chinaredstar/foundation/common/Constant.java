package com.chinaredstar.foundation.common;

import com.chinaredstar.foundation.FoundationApplication;

/**
 * Created by cody.yi on 2016/7/13.
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
        String PORT = ":8080";
        String PREFIX = "/order_api";
        String API_TEST = "/test";
    }

    /**
     * -1       未登录
     * 200	    请求成功  (后面统一规定)
     * 500	    服务端运行异常
     * 10001    验证失败（主要用于验证码模块）
     * 40001	参数缺失
     * 40002	参数非法/参数格式不正确/参数不正确
     * 40003	未找到数据/查询无信息/某某数据不存在
     * 40004	没有权限
     * 40005	不能操作/不能修改（因为某种限制或状态，不能进行操作或修改）
     * 40006	调用依赖服务失败（调用别的项目服务时失败）
     */
    public interface HttpCode {
        String UN_LOGIN = "-1";           //未登录
        String SUCCESS = "200";           //请求成功  (后面统一规定)
        String SERVER_ERROR = "500";       //服务端运行异常
        String AUTH_FAILED = "10001";  //验证失败（主要用于验证码模块）
        String MISSING_PARAMETER  = "40001";     //参数缺失
        String INVALID_PARAMETER  = "40002";     //参数非法/参数格式不正确/参数不正确
        String NOT_FOUND = "40003";     //未找到数据/查询无信息/某某数据不存在
        String PERMISSION_DENIED = "40004";     //没有权限
        String SERVER_REJECT = "40005";     //不能操作/不能修改（因为某种限制或状态，不能进行操作或修改）
        String CALL_SERVICE_FAILURE = "40006";     //调用依赖服务失败（调用别的项目服务时失败）
    }
}
