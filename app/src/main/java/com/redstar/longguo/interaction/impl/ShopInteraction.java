package com.redstar.longguo.interaction.impl;

import android.util.Log;

import com.redstar.foundation.common.Callback;
import com.redstar.foundation.common.Constant;
import com.redstar.foundation.common.utils.HttpUtil;
import com.redstar.foundation.interaction.Interaction;
import com.redstar.longguo.interaction.IShopInteraction;
import com.redstar.longguo.interaction.bean.Shop;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cody.yi on 2016/8/5.
 */
public class ShopInteraction extends Interaction implements IShopInteraction {

    @Override
    public void getShop(Object tag,final Callback callback) {
        Map params = new HashMap();
        params.put("key1", "value1");
        params.put("key2", "value2");
        params.put("key3", "value3");
        callback.onBegin(tag);
        HttpUtil.getData(tag, Constant.HttpUrl.API_TEST, params,
                Shop.class,
                new HttpUtil.Callback<Shop>() {
                    @Override
                    public void onSuccess(Shop data) {
                        Log.d("TAG", "onSuccess Hello World!json=" + data.toString());
                        callback.onSuccess(data);
                    }

                    @Override
                    public void onFailure(String err) {
                        Log.d("TAG", "onFailure Hello World!json=" + err);
                        callback.onFailure(err);
                    }
                });
        callback.onProgress(100,100);
    }
}
