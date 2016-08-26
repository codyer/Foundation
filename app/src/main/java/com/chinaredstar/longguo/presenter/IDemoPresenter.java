package com.chinaredstar.longguo.presenter;

import com.chinaredstar.foundation.presenter.IPresenter;
import com.chinaredstar.foundation.ui.IView;

/**
 * Created by cody.yi on 2016/8/4.
 * 例程
 */
public interface IDemoPresenter<VM,B,V extends IView<VM,B>> extends IPresenter<VM,B,V> {
    /**
     * 事件驱动
     */
    void onGetDemoClick(Object tag);
}
