package com.chinaredstar.foundation.common.utils;

/**
 * Created by cody.yi on 2016/7/18.
 * 网络请求工具
 */

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chinaredstar.foundation.FoundationApplication;
import com.chinaredstar.foundation.common.Constant;
import com.chinaredstar.foundation.common.utils.http.HttpClient;
import com.chinaredstar.foundation.common.utils.http.HttpConnectException;
import com.chinaredstar.foundation.interaction.bean.Result;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理所有数据，对应用提供一致的数据接口，隐藏数据来源
 * 内存、SD卡、网络
 */
public class HttpUtil {
    private static final String TAG = HttpUtil.class.getName();

    /**
     * 调用方式
     */
    public interface Method {
        int GET = 0;
        int POST = 1;
    }

    private HttpUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("HttpUtil cannot be instantiated");
    }

    /**
     * @param tag      当前页面的tag，用于取消request
     * @param method   get:0,post:1
     * @param url      接口Api
     * @param clazz    类型
     * @param callback 数据返回的回调
     * @param <T>      泛型
     */
    public static <T> void getData(String tag, int method, String url, Class<T> clazz, final Callback<T> callback) {
        getData(tag, method, url, null, clazz, callback);
    }

    /**
     * @param tag      当前页面的tag，用于取消request
     * @param url      接口Api
     * @param params   参数
     * @param clazz    类型
     * @param callback 数据返回的回调
     * @param <T>      泛型
     */
    public static <T> void getData(Object tag, int method, String url, Map<String, String> params,
                                   Class<T> clazz, final Callback<T> callback) {
        if (checkParameters(tag, url, callback)) return;

        //添加公用参数
        params = initParams(params);

        //解析Type
        Type type = CommonUtil.getType(Result.class, clazz);

        //加密，解密

        //执行请求
        switch (method) {
            case Method.GET:
                executeGet(tag, url, params, type, callback);
                break;
            case Method.POST:
                executePost(tag, url, params, type, callback);
                break;
            default:
                executePost(tag, url, params, type, callback);
                break;
        }
    }

    /**
     * @param tag      当前页面的tag，用于取消request
     * @param url      接口Api
     * @param clazz    类型
     * @param callback 数据返回的回调
     * @param <T>      泛型
     */
    public static <T> void getListData(Object tag, int method, String url,
                                       Class<T> clazz, final Callback<List<T>> callback) {
        getListData(tag, method, url, null, clazz, callback);
    }

    /**
     * @param tag      当前页面的tag，用于取消request
     * @param url      接口url
     * @param params   参数
     * @param clazz    类型
     * @param callback 数据返回的回调
     * @param <T>      泛型
     */
    public static <T> void getListData(Object tag, int method, String url, Map<String, String> params,
                                       Class<T> clazz, final Callback<List<T>> callback) {
        //检查参数
        if (checkParameters(tag, url, callback)) return;

        //添加公用参数
        params = initParams(params);

        //解析Type
        Type type = CommonUtil.getType(Result.class, CommonUtil.getType(List.class, clazz));

        //加密，解密

        //执行请求
        switch (method) {
            case Method.GET:
                executeGet(tag, url, params, type, callback);
                break;
            case Method.POST:
                executePost(tag, url, params, type, callback);
                break;
            default:
                executePost(tag, url, params, type, callback);
                break;
        }
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
        if (checkParameters(tag, url, callback)) return;

        HttpClient.getInstance().ImageRequest(tag, url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        callback.onSuccess(bitmap);
                    }
                }, maxWidth, maxHeight, scaleType, decodeConfig,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(new HttpConnectException(error.toString()));
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
        if (checkParameters(imageView, imgViewUrl, errorImageResId)) return;

        HttpClient.getInstance().ImageLoaderRequest
                (imageView, imgViewUrl, defaultImageResId, errorImageResId, maxWidth, maxHeight);
    }

    /**
     * 上传图片 multipart
     *
     * @param tag
     * @param url
     * @param imageName
     * @param bitmap
     * @param params
     * @param callback
     */
    public static void uploadImageMultipart(Object tag, String url,
                                            String imageName, Bitmap bitmap,
                                            Map<String, String> params,
                                            final Callback<String> callback) {
        //检查参数
        if (checkParameters(tag, url, callback)) return;

        //添加公用参数
        params = initParams(params);

        //加密，解密

        //执行请求
        LogUtil.d(url);
        HttpClient.getInstance().putImageRequest(tag, url, imageName, bitmap, params,
                new Response.Listener<Result>() {
                    @Override
                    public void onResponse(Result result) {
                        if (Constant.HttpCode.SUCCESS.equals(result.getCode())) {
                            callback.onSuccess((String) result.getData());
                        } else {
                            callback.onFailure(result);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(new HttpConnectException(error.toString()));
                    }
                });
    }

    /**
     * 上传图片 base64
     *
     * @param tag
     * @param url
     * @param imageName
     * @param bitmap
     * @param params
     * @param callback
     */
    public static void uploadImage(Object tag, String url,
                                   String imageName, Bitmap bitmap,
                                   Map<String, String> params,
                                   final Callback<String> callback) {
        //检查参数
        if (checkParameters(tag, url, callback)) return;

        //添加公用参数
        params = initParams(params);

        //加密，解密

        //执行请求
        LogUtil.d(url);
        HttpClient.getInstance().putBase64ImageRequest(tag, url, imageName, bitmap, params,
                new Response.Listener<Result>() {
                    @Override
                    public void onResponse(Result result) {
                        if (Constant.HttpCode.SUCCESS.equals(result.getCode())) {
                            callback.onSuccess((String) result.getData());
                        } else {
                            callback.onFailure(result);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(new HttpConnectException(error.toString()));
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

//        params.put("appVersion", AppUtil.getAppVersionName(FoundationApplication.getContext()));
        return params;
    }

    //检查参数
    private static boolean checkParameters(ImageView imageView, String imgViewUrl, int errorImageResId) {
        if (imageView == null || StringUtil.isEmpty(imgViewUrl)) {
            if (Constant.DEBUG) {
                throw new HttpConnectException("请求参数错误！");
            } else {
                return true;
            }
        }

        //检查网络情况
        if (NetworkUtil.isDisConnected(FoundationApplication.getContext())) {
            imageView.setImageResource(errorImageResId);
            if (Constant.DEBUG) {
                throw new HttpConnectException("无可用的网络连接,请修改网络连接属性！");
            } else {
                return true;
            }
        }
        return false;
    }

    //检查参数
    private static <T> boolean checkParameters(Object tag, String url, Callback<T> callback) {
        if (tag == null || StringUtil.isEmpty(url) || callback == null) {
            if (Constant.DEBUG) {
                throw new HttpConnectException("请求参数错误！");
            } else {
                callback.onError(new HttpConnectException("请求参数错误！"));
                return true;
            }
        }
        //检查网络情况
        if (NetworkUtil.isDisConnected(FoundationApplication.getContext())) {
            if (Constant.DEBUG) {
                throw new HttpConnectException("无可用的网络连接,请修改网络连接属性！");
            } else {
                callback.onError(new HttpConnectException("无可用的网络连接,请修改网络连接属性！"));
                return true;
            }
        }
        return false;
    }

    private static <T> void executeGet(Object tag, String url, Map<String, String> params, Type type, final
    Callback<T> callback) {
        LogUtil.d(url);
        //get请求重建url，拼接参数
        url = reBuildUrl(url, params);
        LogUtil.d("reBuildUrl="+url);
        HttpClient.getInstance().gsonGetRequest(tag, url, params,
                type, new Response.Listener<Result>() {
                    @Override
                    public void onResponse(Result result) {
                        if (Constant.HttpCode.SUCCESS.equals(result.getCode())) {
                            callback.onSuccess((T) result.getData());
                        } else {
                            callback.onFailure(result);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(new HttpConnectException(error.toString()));
                    }
                });
    }

    private static String reBuildUrl(String url, Map<String, String> params) {
        if (params.size() > 0 && StringUtil.isNotEmpty(url)) {
            //restful
            if (url.contains("{")) {
                return reBuildRestFulUrl(url, params);
            }
            url += url.contains("?") ? "&" : "?";
            StringBuilder encodedParams = new StringBuilder();
            try {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    encodedParams.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                    encodedParams.append('=');
                    encodedParams.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                    encodedParams.append('&');
                }
                url += encodedParams.toString();
                url = url.substring(0, url.length() - 1);
            } catch (UnsupportedEncodingException uee) {
                throw new RuntimeException("Encoding not supported: " + "UTF-8", uee);
            }
        }
        return url;
    }

    private static String reBuildRestFulUrl(String url, Map<String, String> params) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            url = url.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return url;
    }

    private static <T> void executePost(Object tag, String url, Map<String, String> params, Type type, final
    Callback<T> callback) {
        LogUtil.d(url);
        HttpClient.getInstance().gsonPostRequest(tag, url, params,
                type, new Response.Listener<Result>() {
                    @Override
                    public void onResponse(Result result) {
                        if (Constant.HttpCode.SUCCESS.equals(result.getCode())) {
                            if (StringUtil.isEmpty(result.getData().toString())) {
                                callback.onSuccess((T) result);
                            } else {
                                callback.onSuccess((T) result.getData());
                            }
                        } else {
                            callback.onFailure(result);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(new HttpConnectException(error.toString()));
                    }
                });
    }

    public interface Callback<T> {
        void onSuccess(T data);

        void onFailure(Result<Object> result);

        void onError(HttpConnectException error);
    }
}
