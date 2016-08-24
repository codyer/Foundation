package com.redstar.longguo.presenter;

import com.redstar.foundation.presenter.IPresenter;

/**
 * Created by cody.yi on 2016/8/4.
 * 例程
 */
public interface IDemoPresenter<V> extends IPresenter<V> {
    /**
     * 事件驱动
     */
    void onGetDemoClick(Object tag);
}
