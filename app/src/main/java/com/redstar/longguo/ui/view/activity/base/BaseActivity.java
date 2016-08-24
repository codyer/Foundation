package com.redstar.longguo.ui.view.activity.base;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.redstar.foundation.presenter.impl.Presenter;
import com.redstar.foundation.ui.view.EventHandler;
import com.redstar.foundation.ui.view.activity.FoundationActivity;
import com.redstar.foundation.ui.viewmodel.ViewModel;

public abstract class BaseActivity<P extends Presenter,VM extends ViewModel,B extends ViewDataBinding> extends FoundationActivity<VM,B> implements EventHandler{

    protected P mPresenter;
    protected abstract P createPresenter();
    /**
     * 子类提供有binding的资源ID
     */
    protected abstract int getLayoutID();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 绑定view
         */
        B b = DataBindingUtil.setContentView(
                this, getLayoutID());
        setBinding(b);
        mPresenter = createPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showException(String msg) {

    }

    @Override
    public void showNetError() {

    }

    @Override
    public void onProgress(long count, long current) {

    }
}
