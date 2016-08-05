package com.redstar.sample.model;

import com.redstar.foundation.common.Callback;
import com.redstar.foundation.model.Model;

/**
 * Created by cody.yi on 2016/8/5.
 */
public interface ShopModel extends Model {
    void getShop(Object tag,Callback callback);
}
