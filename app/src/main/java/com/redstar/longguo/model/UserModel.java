package com.redstar.longguo.model;

import com.redstar.foundation.common.Callback;
import com.redstar.foundation.model.Model;
import com.redstar.longguo.model.bean.User;

import java.util.List;

/**
 * Created by cody.yi on 2016/8/4.
 */
public interface UserModel extends Model {
    void getListUser(Object tag,Callback<List<User>> callback);
}