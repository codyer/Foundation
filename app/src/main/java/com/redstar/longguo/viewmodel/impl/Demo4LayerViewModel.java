package com.redstar.longguo.viewmodel.impl;

import android.databinding.ObservableField;
import android.view.View;

import com.redstar.longguo.model.bean.Shop;
import com.redstar.longguo.model.bean.User;
import com.redstar.longguo.presenter.ShopPresenter;
import com.redstar.longguo.presenter.UserPresenter;
import com.redstar.longguo.presenter.impl.ShopPresenterImpl;
import com.redstar.longguo.presenter.impl.UserPresenterImpl;
import com.redstar.longguo.view.base.BaseActivity;
import com.redstar.longguo.viewmodel.listener.DemoViewListener;

import com.redstar.foundation.common.Callback;
import com.redstar.foundation.common.utils.ToastUtil;
import com.redstar.foundation.viewmodel.ViewModel;

/**
 * Created by cody.yi on 2016/8/4.
 */
public class Demo4LayerViewModel extends ViewModel implements DemoViewListener {
    /**
     * 绑定对应UI属性
     */
    public final ObservableField<String> name = new ObservableField<>();

    /**
     * 实际业务处理，业务相关的变量管理
     */
    private UserPresenter mUserPresenter;
    private ShopPresenter mShopPresenter;

    /**
     * 具体业务处理交由哪个实现来处理，可以根据业务需要进行切换
     * 不应该由View决定交由谁处理
     */
    public Demo4LayerViewModel(BaseActivity activity) {
        super(activity);
        mTag = activity.TAG_LOG;
        mUserPresenter = new UserPresenterImpl();
        mShopPresenter = new ShopPresenterImpl();
    }

    @Override
    public void onLoginClick(View view) {
        if (mUserPresenter.check()){
            mUserPresenter.login(mTag,new Callback<User>(){
                @Override
                public void onSuccess(User user) {
                    super.onSuccess(user);
                    name.set(user.getName());
                }
            });
        }

        mShopPresenter.getShop(mTag,new Callback<Shop>(){
            @Override
            public void onSuccess(Shop obj) {
                super.onSuccess(obj);
                ToastUtil.showToast(obj.toString());
            }

            @Override
            public void onFailure(Object obj) {
                super.onFailure(obj);
                ToastUtil.showToast(obj.toString());
            }
        });
    }

    @Override
    public void onCreate() {
        name.set("OnCreate");
    }

    @Override
    public void onDestroy() {
        mUserPresenter.cancel(mTag);
        mShopPresenter.cancel(mTag);
    }
}
