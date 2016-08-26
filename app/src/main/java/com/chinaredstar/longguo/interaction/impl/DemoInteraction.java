package com.chinaredstarer.longguo.interaction.impl;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.chinaredstarer.foundation.common.Callback;
import com.chinaredstarer.foundation.common.Constant;
import com.chinaredstarer.foundation.common.utils.HttpUtil;
import com.chinaredstarer.foundation.interaction.impl.Interaction;
import com.chinaredstarer.longguo.interaction.IDemoInteraction;
import com.chinaredstarer.longguo.interaction.bean.Demo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cody.yi on 2016/8/5.
 * 例程
 */
public class DemoInteraction extends Interaction implements IDemoInteraction {

    private final static boolean DEBUG = true;

    /**
     * 其他需要清理的引用都在这里处理
     */
    @Override
    public void cancel(Object tag) {
        cancelHttp(tag);
    }

    @Override
    public void getDemo(Object tag,@NonNull final Callback callback) {
        Map params = new HashMap();
        params.put("key1", "value1");
        params.put("key2", "value2");
        params.put("key3", "value3");
        callback.onBegin(tag);
        if (DEBUG) {
            fakeRequestJson(tag, callback, params);
        } else {
            request(tag, callback, params);
        }

        callback.onProgress(100, 100);
    }

    private void fakeRequestJson(Object tag,@NonNull final Callback callback, Map params) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Demo demo = new Demo();
                demo.setId(11);
                demo.setEmail("ssss@sss.com");
                demo.setName("name999");
                callback.onSuccess(demo);
            }
        }, 1000);
    }

    private void request(Object tag,@NonNull final Callback callback, Map params) {
        HttpUtil.getData(tag, Constant.HttpUrl.API_TEST, params,
                Demo.class,
                new HttpUtil.Callback<Demo>() {
                    @Override
                    public void onSuccess(Demo data) {
                        Log.d("TAG", "onSuccess Hello World!json=" + data.toString());
                        callback.onSuccess(data);
                    }

                    @Override
                    public void onFailure(String err) {
                        Log.d("TAG", "onFailure Hello World!json=" + err);
                        callback.onFailure(err);
                    }
                });
    }
}
