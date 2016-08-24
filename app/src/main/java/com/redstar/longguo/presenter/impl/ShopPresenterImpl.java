package com.redstar.longguo.presenter.impl;


import com.redstar.foundation.common.Callback;
import com.redstar.longguo.model.ShopInteraction;
import com.redstar.longguo.model.impl.ShopInteractionImpl;
import com.redstar.longguo.presenter.ShopPresenter;

/**
 * Created by cody.yi on 2016/8/4.
 */
public class ShopPresenterImpl implements ShopPresenter {

    private ShopInteraction mShopModel;

    public ShopPresenterImpl() {
        mShopModel = new ShopInteractionImpl();
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
