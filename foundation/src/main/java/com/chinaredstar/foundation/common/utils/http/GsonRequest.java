package com.chinaredstar.foundation.common.utils.http;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.chinaredstar.foundation.common.utils.LogUtil;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by cody.yi on 2016/7/20.
 * Gson请求封装
 */
public class GsonRequest<T> extends Request<T> {

    private final Response.Listener<T> mListener;
    private Gson mGson = new Gson();
    private Class<T> mClass;
    private Type mType;
    private static Map<String, String> mHeaders;//所有的请求用一个头部，只有在登录之后设置，否则没有头部
    private Map<String, String> mParams;//post Params

    public GsonRequest(int method, String url, Map<String, String> params, Type type,
                       Response.Listener<T> listener,
                       Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mType = type;
        mListener = listener;
        mParams = params;
    }

    public GsonRequest(int method, String url, Map<String, String> params, Class<T> clazz,
                       Response.Listener<T> listener,
                       Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mClass = clazz;
        mListener = listener;
        mParams = params;
    }

    // TODO add token here
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mHeaders == null || mHeaders.isEmpty()? super.getHeaders():mHeaders;
    }

    /**
     * Returns the content type of the POST or PUT body.
     */
//    @Override
//    public String getBodyContentType() {
//        return "application/json; charset=" + getParamsEncoding();
//    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams == null ? super.getParams() : mParams;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            LogUtil.d("parseNetworkResponse response.headers->",response.headers.toString());
            LogUtil.d("parseNetworkResponse->",jsonString);
            if (response.headers.containsKey("x-auth-token")){
                mHeaders = response.headers;
                LogUtil.d("mHeaders->",mHeaders.toString());
            }
            if (mType == null) {
                //用Gson解析返回Java对象
                return Response.success(mGson.fromJson(jsonString, mClass),
                        HttpHeaderParser.parseCacheHeaders(response));
            } else {
                //通过构造TypeToken让Gson解析成自定义的对象类型
                return (Response<T>) Response.success(mGson.fromJson(jsonString, mType),
                        HttpHeaderParser.parseCacheHeaders(response));
            }
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    protected static ParameterizedType getType(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }
}