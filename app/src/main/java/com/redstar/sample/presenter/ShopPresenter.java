package com.redstar.sample.presenter;

import com.redstar.foundation.common.Callback;
import com.redstar.foundation.presenter.Presenter;

/**
 * Created by cody.yi on 2016/8/4.
 */
public interface ShopPresenter extends Presenter {
    void getShop(Object tag,Callback callback);
}
