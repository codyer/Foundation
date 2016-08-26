package com.chinaredstarer.foundation.common;

import com.chinaredstarer.foundation.common.utils.LogUtil;

/**
 * Created by cody.yi on 2016/8/5.
 *
 * 所有View和ViewModel，ViewModel和Presenter之间的回调使用这个Callback
 */
public abstract class Callback<T> {

    // 操作执行前
    public void onBegin(Object obj) {
        LogUtil.i("Callback onBegin"+obj);
    }

    //操作执行结束
    public void onSuccess(T obj) {
        LogUtil.i("Callback onSuccess" + obj);
    }

    //执行出错
    public void onFailure(Object obj) {
        LogUtil.i("Callback onFailure"+obj);
    }

    //执行取消
    public void onCancel(Object obj) {
        LogUtil.i("Callback onCancelled"+obj);
    }

    //执行进度
    public void onProgress(long count, long current) {
        LogUtil.i("Callback onProgress count="+count+" current="+current);
    }
}
