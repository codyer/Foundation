package com.redstar.longguo.presenter.impl;


import com.redstar.foundation.common.Callback;
import com.redstar.longguo.model.ShopModel;
import com.redstar.longguo.model.impl.ShopModelImpl;
import com.redstar.longguo.presenter.ShopPresenter;

/**
 * Created by cody.yi on 2016/8/4.
 */
public class ShopPresenterImpl implements ShopPresenter {

    private ShopModel mShopModel;

    public ShopPresenterImpl() {
        mShopModel = new ShopModelImpl();
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
