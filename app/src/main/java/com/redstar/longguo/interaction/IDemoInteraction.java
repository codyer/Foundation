package com.redstar.longguo.interaction;

import android.support.annotation.NonNull;

import com.redstar.foundation.common.Callback;
import com.redstar.foundation.interaction.IInteraction;

/**
 * Created by cody.yi on 2016/8/5.
 */
public interface IDemoInteraction extends IInteraction {
    /**
     * 定义接口，获取数据
     */
    void getDemo(Object tag,@NonNull final Callback callback);
}
