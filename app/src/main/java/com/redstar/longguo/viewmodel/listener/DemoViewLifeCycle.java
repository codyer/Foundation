package com.redstar.longguo.viewmodel.listener;

import android.view.View;
import com.redstar.foundation.viewmodel.ViewLifeCycle;

/**
 * Created by cody.yi on 2016/8/4.
 */
public interface DemoViewLifeCycle extends ViewLifeCycle {
    /**
     * 直接在xml中绑定，也可以在Activity中监听事件并调用
     */
    void onLoginClick(View view);
}
