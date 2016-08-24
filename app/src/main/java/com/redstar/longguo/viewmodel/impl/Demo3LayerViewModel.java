package com.redstar.longguo.viewmodel.impl;

import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import com.redstar.foundation.common.Callback;
import com.redstar.foundation.common.utils.ToastUtil;
import com.redstar.foundation.viewmodel.ViewModel;
import com.redstar.longguo.interaction.IShopInteraction;
import com.redstar.longguo.interaction.IUserInteraction;
import com.redstar.longguo.interaction.bean.Shop;
import com.redstar.longguo.interaction.bean.User;
import com.redstar.longguo.interaction.impl.ShopInteraction;
import com.redstar.longguo.interaction.impl.UserInteraction;
import com.redstar.longguo.view.activity.MainActivity;
import com.redstar.longguo.view.activity.base.BaseActivity;
import com.redstar.longguo.viewmodel.listener.DemoViewLifeCycle;

import java.util.List;

/**
 * Created by cody.yi on 2016/8/4.
 */
public class Demo3LayerViewModel extends ViewModel implements DemoViewLifeCycle {
    /**
     * 绑定对应UI属性
     */
    public final ObservableField<String> name = new ObservableField<>();

    /**
     * 实际业务处理，业务相关的变量管理
     */
    private IUserInteraction mUserModel;
    private IShopInteraction mShopModel;

    /**
     * 具体业务处理交由哪个实现来处理，可以根据业务需要进行切换
     * 不应该由View决定交由谁处理
     */
    public Demo3LayerViewModel(BaseActivity activity) {
        super(activity);
        mTag = activity.TAG;
        mContext = activity;
        mUserModel = new UserInteraction();
        mShopModel = new ShopInteraction();
    }

    @Override
    public void onLoginClick(View view) {
        mUserModel.getUsers(mTag, new Callback<List<User>>() {
            @Override
            public void onSuccess(List<User> userList) {
                name.set(userList.get(0).getName());
            }
        });

        mShopModel.getShop(mTag, new Callback<Shop>() {
            @Override
            public void onSuccess(Shop obj) {
                ToastUtil.showToast(obj.toString());
            }

            @Override
            public void onFailure(Object obj) {
                ToastUtil.showToast(obj.toString());
            }
        });

        Intent intent = new Intent(mContext, MainActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public void onCreate() {
        name.set("OnCreate");
    }

    @Override
    public void onDestroy() {
        mShopModel.cancel(mTag);
        mUserModel.cancel(mTag);
    }
}
