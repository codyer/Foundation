package com.redstar.longguo.model;

import com.redstar.foundation.common.Callback;
import com.redstar.foundation.interaction.Interaction;
import com.redstar.longguo.model.bean.User;

import java.util.List;

/**
 * Created by cody.yi on 2016/8/4.
 */
public interface UserInteraction extends Interaction {
    void getListUser(Object tag,Callback<List<User>> callback);
}
