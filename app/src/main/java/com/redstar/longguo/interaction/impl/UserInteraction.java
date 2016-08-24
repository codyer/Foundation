package com.redstar.longguo.interaction.impl;

import com.redstar.foundation.common.Callback;
import com.redstar.foundation.common.Constant;
import com.redstar.foundation.common.utils.HttpUtil;
import com.redstar.foundation.interaction.Interaction;
import com.redstar.longguo.interaction.IUserInteraction;
import com.redstar.longguo.interaction.bean.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cody.yi on 2016/8/4.
 */
public class UserInteraction extends Interaction implements IUserInteraction {

    @Override
    public void getUsers(Object tag, final Callback<List<User>> callback) {
        callback.onBegin(tag);
        Map params = new HashMap();
        params.put("key1", "value1");
        params.put("key2", "value2");
        params.put("key3", "value3");
        HttpUtil.getListData(tag, Constant.HttpUrl.API_TEST_LIST, params, User.class, new HttpUtil.Callback<List<User>>() {
            @Override
            public void onSuccess(List<User> data) {
                callback.onSuccess(data);
            }

            @Override
            public void onFailure(String err) {
                callback.onFailure(err);
            }
        });
    }
}
