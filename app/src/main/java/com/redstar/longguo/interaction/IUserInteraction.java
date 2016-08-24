package com.redstar.longguo.interaction;

import com.redstar.foundation.common.Callback;
import com.redstar.foundation.interaction.IInteraction;
import com.redstar.longguo.interaction.bean.User;

import java.util.List;

/**
 * Created by cody.yi on 2016/8/4.
 */
public interface IUserInteraction extends IInteraction {
    void getUsers(Object tag, Callback<List<User>> callback);
}
