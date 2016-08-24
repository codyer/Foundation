package com.redstar.longguo.presenter;

import com.redstar.foundation.common.Callback;
import com.redstar.foundation.presenter.IPresenter;
import com.redstar.longguo.interaction.bean.User;

/**
 * Created by cody.yi on 2016/8/4.
 */
public interface UserPresenter extends IPresenter {
    boolean check();
    boolean login(Object tag,Callback<User> callback);
    boolean logout(Object tag,Callback callback);
}
