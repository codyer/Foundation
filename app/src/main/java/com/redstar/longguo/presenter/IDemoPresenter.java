package com.redstar.longguo.presenter;

import com.redstar.foundation.common.Callback;
import com.redstar.foundation.presenter.IPresenter;

/**
 * Created by cody.yi on 2016/8/4.
 */
public interface IDemoPresenter extends IPresenter {
    void getDemo(Object tag, Callback callback);
}
