package com.redstar.longguo.ui.view.activity.base;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.redstar.foundation.common.utils.LogUtil;
import com.redstar.foundation.presenter.impl.Presenter;
import com.redstar.foundation.ui.view.EventHandler;
import com.redstar.foundation.ui.view.activity.FoundationActivity;
import com.redstar.foundation.ui.viewmodel.ViewModel;

import org.antlr.v4.runtime.misc.NotNull;

public abstract class BaseActivity<P extends Presenter,VM extends ViewModel,B extends ViewDataBinding> extends FoundationActivity<VM,B> implements EventHandler{

    protected P mPresenter;

    /**
     * 每个view保证只有一个ViewModel，当包含其他ViewModel时使用根ViewModel包含子ViewModel
     */
    protected abstract@NotNull VM createViewModel();

    /**
     * 每个view保证只有一个Presenter
     */
    protected abstract@NotNull P createPresenter();

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
        setViewModel(createViewModel());
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
        LogUtil.d("BaseActivity ++ showLoading");
    }

    @Override
    public void hideLoading() {
        LogUtil.d("BaseActivity ++ hideLoading");
    }

    @Override
    public void showError(String msg) {
        LogUtil.d("BaseActivity ++ showError");
    }

    @Override
    public void showException(String msg) {
        LogUtil.d("BaseActivity ++ showException");
    }

    @Override
    public void showNetError() {
        LogUtil.d("BaseActivity ++ showNetError");
    }

    @Override
    public void onProgress(long count, long current) {
        LogUtil.d("BaseActivity ++ onProgress");
    }
}
