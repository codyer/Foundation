package com.chinaredstar.foundation.common.utils.http;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import com.chinaredstar.foundation.FoundationApplication;
import com.chinaredstar.foundation.common.utils.ImageUtil;
import com.chinaredstar.foundation.interaction.bean.Result;

/**
 * Created by cody.yi on 2016/7/13.
 */
public class HttpClient {
    private static HttpClient sHttpClient = null;
    private ImageLoader mImageLoader;
    private RequestQueue mRequestQueue;

    private HttpClient(Context context) {
        mRequestQueue = Volley.newRequestQueue(context, new OkHttpStack(new OkHttpClient()));
        mImageLoader = new ImageLoader(mRequestQueue, new LruBitmapCache(context));
    }

    /**
     * 单例模式（静态内部类）
     *
     * @return HttpManager instance
     */
    public static synchronized HttpClient getInstance() {
        if (sHttpClient == null) {
            sHttpClient = new HttpClient(FoundationApplication.getContext());
        }
        return sHttpClient;
    }

    private <T> Request<T> add(Request<T> request) {
        return mRequestQueue.add(request);//添加请求到队列
    }

    /**
     * @param tag 页面tag
     * @param url 请求地址
     * @param listener 成功回调
     * @param errorListener 失败回调
     */
    public StringRequest StrRequest(Object tag, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        StringRequest request = new StringRequest(url, listener, errorListener);
        request.setTag(tag);
        add(request);
        return request;
    }

    /**
     * @param tag 页面tag
     * @param method 请求方式
     * @param url 请求地址
     * @param listener 成功回调
     * @param errorListener 失败回调
     */
    public StringRequest StrRequest(Object tag, int method, String url, Response.Listener<String> listener,
                                    Response.ErrorListener errorListener) {
        StringRequest request = new StringRequest(method, url, listener, errorListener);
        request.setTag(tag);
        add(request);
        return request;
    }

    /**
     * ImageRequest
     *
     * @param tag
     * @param url
     * @param listener
     * @param maxWidth
     * @param maxHeight
     * @param scaleType
     * @param decodeConfig
     * @param errorListener
     * @return
     */
    public ImageRequest ImageRequest(Object tag, String url, Response.Listener<Bitmap> listener,
                                     int maxWidth, int maxHeight, ImageView.ScaleType scaleType,
                                     Bitmap.Config decodeConfig, Response.ErrorListener errorListener) {
        ImageRequest request = new ImageRequest(url, listener, maxWidth, maxHeight, scaleType,
                decodeConfig, errorListener);
        request.setTag(tag);
        add(request);
        return request;
    }

    /**
     * ImageLoader 图片默认大小
     *
     * @param imageView 图片控件
     * @param imgViewUrl 图片地址
     * @param defaultImageResId 默认图片id
     * @param errorImageResId 出错图片id
     */
    public void ImageLoaderRequest(ImageView imageView, String imgViewUrl, int defaultImageResId,
                                   int errorImageResId) {
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, defaultImageResId,
                errorImageResId);
        mImageLoader.get(imgViewUrl, listener);
    }


    /**
     * ImageLoader 指定图片大小
     *
     * @param imageView 图片控件
     * @param imgViewUrl 图片地址
     * @param defaultImageResId 默认图片id
     * @param errorImageResId 出错图片id
     * @param maxWidth 最大宽度
     * @param maxHeight 最大高度
     */
    public void ImageLoaderRequest(ImageView imageView, String imgViewUrl, int defaultImageResId,
                                   int errorImageResId, int maxWidth, int maxHeight) {
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, defaultImageResId,
                errorImageResId);
        mImageLoader.get(imgViewUrl, listener, maxWidth, maxHeight);
    }

    /**
     * Get方式1.1：Map参数
     *
     * @param tag 页面tag
     * @param url 请求地址
     * @param type 类型
     * @param listener 成功回调
     * @param errorListener 失败回调
     * @return 泛型
     */
    public <T> GsonRequest<T> gsonGetRequest(Object tag, String url,
                                             Type type, Response.Listener<T> listener,
                                             Response.ErrorListener errorListener) {
        GsonRequest<T> request = new GsonRequest<>(Request.Method.GET, url, null, type, listener, errorListener);
        request.setTag(tag);
        add(request);
        return request;
    }
    /**
     * Get1.2方法
     *
     * @param tag 页面tag
     * @param url 请求地址
     * @param clazz 返回bean类型
     * @param listener 成功回调
     * @param errorListener 失败回调
     * @param <T> bean
     * @return
     */
    public <T> GsonRequest<T> gsonGetRequest(Object tag, String url,
                                             Class<T> clazz, Response.Listener<T> listener,
                                             Response.ErrorListener errorListener) {
        GsonRequest<T> request = new GsonRequest<>(Request.Method.GET,url,null, clazz, listener, errorListener);
        request.setTag(tag);
        add(request);
        return request;
    }
    /**
     * Post方式1.1：Map参数
     *
     * @param tag 页面tag
     * @param params 请求参数
     * @param url 请求地址
     * @param type 类型
     * @param listener 成功回调
     * @param errorListener 失败回调
     * @return 泛型
     */
    public <T> GsonRequest<T> gsonPostRequest(Object tag, String url, Map<String, String> params,
                                              Type type, Response.Listener<T> listener,
                                              Response.ErrorListener errorListener) {
        GsonRequest<T> request = new GsonRequest<>(Request.Method.POST, url, params, type, listener, errorListener);
        request.setTag(tag);
        add(request);
        return request;
    }

    /**
     * Post方式1.2：Map参数
     *
     * @param tag 页面tag
     * @param params 请求参数
     * @param url 请求地址
     * @param clazz clazz
     * @param listener 成功回调
     * @param errorListener 失败回调
     * @return 泛型
     */
    public <T> GsonRequest<T> gsonPostRequest(Object tag, String url, Map<String, String> params,
                                              Class<T> clazz, Response.Listener<T> listener,
                                              Response.ErrorListener errorListener) {
        GsonRequest<T> request = new GsonRequest<>(Request.Method.POST,url, params, clazz, listener, errorListener);
        request.setTag(tag);
        add(request);
        return request;
    }

    /**
     * Post方式2：json字符串
     * 获取json对象
     *  @param url 请求地址
     * @param jsonObject 参数
     * @param listener 成功的回调函数
     * @param errorListener 失败回调函数
     */
    public JsonObjectRequest postJsonObjectRequest(Object tag, String url, JSONObject jsonObject, Response.Listener<JSONObject> listener,
                                                   Response.ErrorListener errorListener) {
        JsonObjectRequest request;
        request = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                listener, errorListener);
        request.setTag(tag);
        add(request);
        return request;
    }

    /**
     * Post方式3：json字符串
     * 获取json数组
     *  @param tag 页面tag
     * @param url 请求地址
     * @param jsonRequest 请求参数
     * @param listener 成功的回调函数
     * @param errorListener 失败回调函数
     */
    public JsonArrayRequest postJsonArrayRequest(Object tag, String url, JSONArray jsonRequest, Response.Listener<JSONArray> listener,
                                                 Response.ErrorListener errorListener) {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, jsonRequest, listener, errorListener);
        request.setTag(tag);
        add(request);
        return request;
    }

    /**
     * Put：base64图片
     *
     *  @param tag 页面tag
     * @param url 请求地址
     * @param listener 成功的回调函数
     * @param errorListener 失败回调函数
     */
    public UploadBase64ImageRequest putBase64ImageRequest(Object tag, String url,
                                                          String imageName, Bitmap bitmap,
                                                          Map<String, String> params,
                                                          Response.Listener<Result> listener,
                                                          Response.ErrorListener errorListener) {
        UploadBase64ImageRequest request = new UploadBase64ImageRequest(url,imageName,bitmap,params,listener,errorListener);
        request.setTag(tag);
        add(request);
        return request;
    }
    /**
     * Put：multipart图片
     *
     * @param tag 页面tag
     * @param url 请求地址
     * @param byteData 需要上传的文件信息
     * @param listener 成功的回调函数
     * @param errorListener 失败回调函数
     */
    public MultipartRequest putImageRequest(Object tag, String url,
                                            Map<String, DataPart> byteData,
                                            Map<String, String> params,
                                            Response.Listener<Result> listener,
                                            Response.ErrorListener errorListener) {
        MultipartRequest request = new MultipartRequest(url,params,byteData,listener,errorListener);
        request.setTag(tag);
        add(request);
        return request;
    }


    public MultipartRequest putImageRequest(Object tag, String url, String imageName, Bitmap bitmap,
                                            Map<String, String> params, Response.Listener<Result> listener,
                                            Response.ErrorListener errorListener) {
        Map<String, DataPart> byteData = new HashMap<>();
        byteData.put("image",new DataPart(imageName, ImageUtil.getFileDataFromBitmap(bitmap),"image/jpeg"));
        MultipartRequest request = new MultipartRequest(url,params,byteData,listener,errorListener);
        request.setTag(tag);
        add(request);
        return request;
    }
    /**
     * 取消请求 酌情在Activity或其他组件的onStop中调用
     *
     * @param tag
     */
    public void cancel(Object tag) {
        mRequestQueue.cancelAll(tag);
    }

}
