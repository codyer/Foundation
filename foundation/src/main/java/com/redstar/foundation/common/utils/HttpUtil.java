package com.redstar.foundation.common.utils;

/**
 * Created by cody.yi on 2016/7/18.
 */

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.redstar.foundation.FoundationApplication;
import com.redstar.foundation.common.Constant;
import com.redstar.foundation.common.utils.http.HttpClient;
import com.redstar.foundation.common.utils.http.HttpConnectException;
import com.redstar.foundation.model.bean.Result;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理所有数据，对应用提供一致的数据接口，隐藏数据来源
 * 内存、SD卡、网络
 */
public class HttpUtil {
    private static final String TAG = HttpUtil.class.getName();

    private HttpUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("HttpUtil cannot be instantiated");
    }

    /**
     * @param tag      当前页面的tag，用于取消request
     * @param api      接口Api
     * @param clazz    类型
     * @param callback 数据返回的回调
     * @param <T>      泛型
     */
    public static <T> void getData(String tag, String api, Class<T> clazz, final Callback<T> callback) {
        getData(tag, api, null, clazz, callback);
    }

    /**
     * @param tag      当前页面的tag，用于取消request
     * @param api      接口Api
     * @param params   参数
     * @param clazz    类型
     * @param callback 数据返回的回调
     * @param <T>      泛型
     */
    public static <T> void getData(Object tag, String api, Map<String, String> params,
                                   Class<T> clazz, final Callback<T> callback) {
        //检查参数
        if (tag == null || StringUtil.isEmpty(api) || callback == null) {
            if (Constant.DEBUG){
                throw new HttpConnectException("请求参数错误！");
            }else{
                callback.onFailure("请求参数错误！");
                return;
            }
        }
        //检查网络情况
        if (NetworkUtil.isDisConnected(FoundationApplication.getContext())) {
            if (Constant.DEBUG){
                throw new HttpConnectException("无可用的网络连接,请修改网络连接属性！");
            }else{
                callback.onFailure("无可用的网络连接,请修改网络连接属性！");
                return;
            }
        }
        //添加公用参数
        params = initParams(params);

        //解析Type
        Type type = CommonUtil.getType(Result.class, clazz);

        //加密，解密

        //执行请求
        executePost(tag, api, params, type, callback);
    }

    /**
     * @param tag      当前页面的tag，用于取消request
     * @param api      接口Api
     * @param clazz    类型
     * @param callback 数据返回的回调
     * @param <T>      泛型
     */
    public static <T> void getListData(Object tag, String api,
                                       Class<T> clazz, final Callback<List<T>> callback) {
        getListData(tag, api, null, clazz, callback);
    }

    /**
     * @param tag      当前页面的tag，用于取消request
     * @param api      接口Api
     * @param params   参数
     * @param clazz    类型
     * @param callback 数据返回的回调
     * @param <T>      泛型
     */
    public static <T> void getListData(Object tag, String api, Map<String, String> params,
                                       Class<T> clazz, final Callback<List<T>> callback) {
        //检查参数
        if (tag == null || StringUtil.isEmpty(api) || callback == null) {
            if (Constant.DEBUG){
                throw new HttpConnectException("请求参数错误！");
            }else{
                callback.onFailure("请求参数错误！");
                return;
            }
        }
        //检查网络情况
        if (NetworkUtil.isDisConnected(FoundationApplication.getContext())) {
            if (Constant.DEBUG){
                throw new HttpConnectException("无可用的网络连接,请修改网络连接属性！");
            }else{
                callback.onFailure("无可用的网络连接,请修改网络连接属性！");
                return;
            }
        }
        //添加公用参数
        params = initParams(params);
        //解析Type
        Type type = CommonUtil.getType(Result.class, CommonUtil.getType(List.class, clazz));
//        Type type = CommonUtil.getType(Result.class, List.class, clazz);
        //加密，解密

        //执行请求
        executePost(tag, api, params, type, callback);
    }

    private static <T> void executePost(Object tag, String api, Map<String, String> params, Type type, final Callback<T> callback) {
        String url = Constant.HttpUrl.SERVER + Constant.HttpUrl.PORT + Constant.HttpUrl.PREFIX + api;
        LogUtil.d(url);
        HttpClient.getInstance().gsonPostRequest(tag, url, params,
                type, new Response.Listener<Result>() {
                    @Override
                    public void onResponse(Result result) {
                        if (Constant.HttpCode.SUCCESS.equals(result.getCode())) {
                            callback.onSuccess((T) result.getData());
                        } else {
                            callback.onFailure(result.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onFailure(error.toString());
                    }
                });
    }

    /**
     * 异步获取图片，已经包含缓存处理
     *
     * @param tag
     * @param url
     * @param callback
     */
    public static void getImage(Object tag, String url, final Callback<Bitmap> callback) {
        getImage(tag, url, 0, 0, ImageView.ScaleType.CENTER_INSIDE, null, callback);
    }

    /**
     * 异步获取图片，已经包含缓存处理
     *
     * @param tag
     * @param url
     * @param maxWidth
     * @param maxHeight
     * @param scaleType
     * @param decodeConfig
     * @param callback
     */
    public static void getImage(Object tag, String url, int maxWidth, int maxHeight,
                                ImageView.ScaleType scaleType,
                                Bitmap.Config decodeConfig,
                                final Callback<Bitmap> callback) {
        //检查参数
        if (tag == null || StringUtil.isEmpty(url)) {
            if (Constant.DEBUG){
                throw new HttpConnectException("请求参数错误！");
            }else{
                callback.onFailure("请求参数错误！");
                return;
            }
        }

        //检查网络情况
        if (NetworkUtil.isDisConnected(FoundationApplication.getContext())) {
            if (Constant.DEBUG){
                throw new HttpConnectException("无可用的网络连接,请修改网络连接属性！");
            }else{
                callback.onFailure("无可用的网络连接,请修改网络连接属性！");
                return;
            }
        }

        HttpClient.getInstance().ImageRequest(tag, url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        callback.onSuccess(bitmap);
                    }
                }, maxWidth, maxHeight, scaleType, decodeConfig,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        callback.onFailure(error.toString());
                    }
                });
    }

    /**
     * 异步获取图片，但是图片加载结果交由image load自动监听
     *
     * @param imageView
     * @param imgViewUrl
     * @param defaultImageResId
     * @param errorImageResId
     */
    public static void loadImage(ImageView imageView, String imgViewUrl, int defaultImageResId,
                                 int errorImageResId) {
        loadImage(imageView, imgViewUrl, defaultImageResId, errorImageResId, 0, 0);
    }

    /**
     * 异步获取图片，但是图片加载结果交由image load自动监听
     *
     * @param imageView
     * @param imgViewUrl
     * @param defaultImageResId
     * @param errorImageResId
     * @param maxWidth
     * @param maxHeight
     */
    public static void loadImage(ImageView imageView, String imgViewUrl, int defaultImageResId,
                                 int errorImageResId, int maxWidth, int maxHeight) {
        //检查参数
        if (imageView == null || StringUtil.isEmpty(imgViewUrl)) {
            if (Constant.DEBUG){
                throw new HttpConnectException("请求参数错误！");
            }else{
                return;
            }
        }

        //检查网络情况
        if (NetworkUtil.isDisConnected(FoundationApplication.getContext())) {
            imageView.setImageResource(errorImageResId);
            if (Constant.DEBUG){
                throw new HttpConnectException("无可用的网络连接,请修改网络连接属性！");
            }else{
                return;
            }
        }

        HttpClient.getInstance().ImageLoaderRequest
                (imageView, imgViewUrl, defaultImageResId, errorImageResId, maxWidth, maxHeight);
    }

    /**
     * 上传图片 multipart
     *
     * @param tag
     * @param api
     * @param imageName
     * @param bitmap
     * @param params
     * @param callback
     */
    public static void uploadImageMultipart(Object tag, String api,
                                            String imageName, Bitmap bitmap,
                                            Map<String, String> params,
                                            final Callback<String> callback) {
        //检查参数
        if (tag == null || StringUtil.isEmpty(api) || callback == null) {
            if (Constant.DEBUG){
                throw new HttpConnectException("请求参数错误！");
            }else{
                callback.onFailure("请求参数错误！");
                return;
            }
        }
        //检查网络情况
        if (NetworkUtil.isDisConnected(FoundationApplication.getContext())) {
            if (Constant.DEBUG){
                throw new HttpConnectException("无可用的网络连接,请修改网络连接属性！");
            }else{
                callback.onFailure("无可用的网络连接,请修改网络连接属性！");
                return;
            }
        }
        //添加公用参数
        params = initParams(params);

        //加密，解密

        //执行请求
        String url = Constant.HttpUrl.SERVER + Constant.HttpUrl.PORT + Constant.HttpUrl.PREFIX + api;
        LogUtil.d(url);
        HttpClient.getInstance().putImageRequest(tag, url, imageName, bitmap, params,
                new Response.Listener<Result>() {
                    @Override
                    public void onResponse(Result result) {
                        if (Constant.HttpCode.SUCCESS.equals(result.getCode())) {
                            callback.onSuccess((String) result.getData());
                        } else {
                            callback.onFailure(result.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onFailure(error.toString());
                    }
                });
    }

    /**
     * 上传图片 base64
     *
     * @param tag
     * @param api
     * @param imageName
     * @param bitmap
     * @param params
     * @param callback
     */
    public static void uploadImage(Object tag, String api,
                                   String imageName, Bitmap bitmap,
                                   Map<String, String> params,
                                   final Callback<String> callback) {
        //检查参数
        if (tag == null || StringUtil.isEmpty(api) || callback == null) {
            if (Constant.DEBUG){
                throw new HttpConnectException("请求参数错误！");
            }else{
                callback.onFailure("请求参数错误！");
                return;
            }
        }
        //检查网络情况
        if (NetworkUtil.isDisConnected(FoundationApplication.getContext())) {
            if (Constant.DEBUG){
                throw new HttpConnectException("无可用的网络连接,请修改网络连接属性！");
            }else{
                callback.onFailure("无可用的网络连接,请修改网络连接属性！");
                return;
            }
        }
        //添加公用参数
        params = initParams(params);

        //加密，解密

        //执行请求
        String url = Constant.HttpUrl.SERVER + Constant.HttpUrl.PORT + Constant.HttpUrl.PREFIX + api;
        LogUtil.d(url);
        HttpClient.getInstance().putBase64ImageRequest(tag, url, imageName, bitmap, params,
                new Response.Listener<Result>() {
                    @Override
                    public void onResponse(Result result) {
                        if (Constant.HttpCode.SUCCESS.equals(result.getCode())) {
                            callback.onSuccess((String) result.getData());
                        } else {
                            callback.onFailure(result.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onFailure(error.toString());
                    }
                });
    }

    /**
     * 取消请求 酌情在Activity或其他组件的onStop中调用
     *
     * @param tag
     */
    public static void cancel(Object tag) {
        HttpClient.getInstance().cancel(tag);
    }

    private static Map<String, String> initParams(Map<String, String> params) {
        if (params == null) {
            params = new HashMap<>();
        }
//        params.put("deviceId", AppTools.getDeviceId());
//        params.put("sessionId", AppTools.getSessionId());
//        params.put("timeStamp", AppTools.getTimeStamp());
//        params.put("format", "JSON");
//        params.put("version", "1.0");
//        params.put("signMethod", "method");
//        params.put("appKey", "100000");

        params.put("appVersion", AppUtil.getAppVersionName(FoundationApplication.getContext()));
        return params;
    }


    public interface Callback<T> {
        void onSuccess(T data);

        void onFailure(String err);
    }
}
