package com.redstar.longguo.presenter;

import com.redstar.foundation.presenter.IPresenter;
import com.redstar.longguo.ui.view.activity.DemoActivity;

/**
 * Created by cody.yi on 2016/8/4.
 * 例程
 */
public interface IDemoPresenter extends IPresenter<DemoActivity> {
    /**
     * 事件驱动
     */
    void onGetDemoClick(Object tag);
}
