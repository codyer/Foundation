package com.chinaredstar.longguo.interaction;

import android.support.annotation.NonNull;

import com.chinaredstar.foundation.common.Callback;
import com.chinaredstar.foundation.interaction.IInteraction;

/**
 * Created by cody.yi on 2016/8/5.
 */
public interface IDemoInteraction extends IInteraction {
    /**
     * 定义接口，获取数据
     */
    void getDemo(Object tag,@NonNull final Callback callback);
}
