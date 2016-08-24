package com.redstar.longguo.model;

import com.redstar.foundation.common.Callback;
import com.redstar.foundation.interaction.Interaction;

/**
 * Created by cody.yi on 2016/8/5.
 */
public interface ShopInteraction extends Interaction {
    void getShop(Object tag,Callback callback);
}
