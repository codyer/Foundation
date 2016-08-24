package com.redstar.longguo.presenter.impl;


import com.redstar.foundation.common.Callback;
import com.redstar.longguo.model.UserInteraction;
import com.redstar.longguo.model.bean.User;
import com.redstar.longguo.model.impl.UserInteractionImpl;
import com.redstar.longguo.presenter.UserPresenter;

import java.util.List;

/**
 * Created by cody.yi on 2016/8/4.
 */
public class UserPresenterImpl implements UserPresenter {

    private UserInteraction mUserModel;
    private boolean isLogin;

    public UserPresenterImpl() {
        mUserModel = new UserInteractionImpl();
    }

    @Override
    public boolean check() {
        isLogin = true;
        return true;
    }

    @Override
    public boolean login(Object tag, final Callback<User> callback) {
        mUserModel.getListUser(tag, new Callback<List<User>>() {
            @Override
            public void onBegin(Object obj) {
                callback.onBegin(obj);
            }

            @Override
            public void onSuccess(List<User> data) {
                callback.onSuccess(data.get(data.size()-1));
            }

            @Override
            public void onFailure(Object obj) {
                callback.onFailure(obj);
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
