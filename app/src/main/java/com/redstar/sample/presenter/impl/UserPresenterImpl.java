package com.redstar.sample.presenter.impl;


import com.redstar.foundation.common.Callback;
import com.redstar.foundation.utils.HttpUtil;
import com.redstar.sample.model.UserModel;
import com.redstar.sample.model.bean.User;
import com.redstar.sample.model.impl.UserModelImpl;
import com.redstar.sample.presenter.UserPresenter;

import java.util.List;

/**
 * Created by cody.yi on 2016/8/4.
 */
public class UserPresenterImpl implements UserPresenter {

    private UserModel mUserModel;
    private boolean isLogin;

    public UserPresenterImpl() {
        mUserModel = new UserModelImpl();
    }

    @Override
    public boolean check() {
        isLogin = true;
        return true;
    }

    @Override
    public boolean login(Object tag, final Callback<User> callback) {
        callback.onBegin(tag);
        mUserModel.getListUser(tag, new HttpUtil.Callback<List<User>>() {

            @Override
            public void onSuccess(List<User> data) {
                callback.onSuccess(data.get(0));
            }

            @Override
            public void onFailure(String err) {
                callback.onFailure(err);
            }
        });
        callback.onProgress(100,100);
        isLogin = true;
        return true;
    }

    @Override
    public boolean logout(Object tag,Callback callback) {
        isLogin = false;
        return true;
    }

    @Override
    public boolean cancel(Object tag) {
        // 耗时操作cancel
        mUserModel.cancel(tag);
        return true;
    }
}
