package com.chinaredstar.foundation.common;

import com.chinaredstar.foundation.common.utils.LogUtil;
import com.chinaredstar.foundation.interaction.bean.SimpleBean;

/**
 * Created by cody.yi on 2016/8/5.
 *
 * 所有View和ViewModel，ViewModel和Presenter之间的回调使用这个Callback
 */
public abstract class Callback<T> {

    // 操作执行前
    public void onBegin(Object tag) {
        LogUtil.i("Callback onBegin"+tag);
    }

    //操作执行结束
    public void onSuccess(T bean) {
        LogUtil.i("Callback onSuccess" + bean);
    }

    //执行出错
    public void onFailure(SimpleBean simpleBean) {
        LogUtil.i("Callback onFailure"+simpleBean);
    }

    //执行出错并取消
    public void onError(SimpleBean simpleBean) {
        LogUtil.i("Callback onError"+simpleBean);
    }

    //执行进度
    public void onProgress(long count, long current) {
        LogUtil.i("Callback onProgress count="+count+" current="+current);
    }
}
