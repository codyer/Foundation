package com.redstar.longguo.presenter.impl;


import com.redstar.foundation.common.Callback;
import com.redstar.longguo.interaction.IShopInteraction;
import com.redstar.longguo.interaction.impl.ShopInteraction;
import com.redstar.longguo.presenter.ShopPresenter;

/**
 * Created by cody.yi on 2016/8/4.
 */
public class ShopPresenterImpl implements ShopPresenter {

    private IShopInteraction mShopModel;

    public ShopPresenterImpl() {
        mShopModel = new ShopInteraction();
    }

    @Override
    public boolean cancel(Object tag) {
        // 耗时操作cancel
        mShopModel.cancel(tag);
        return true;
    }

    @Override
    public void getShop(Object tag,Callback callback) {
        mShopModel.getShop(tag,callback);
    }
}
