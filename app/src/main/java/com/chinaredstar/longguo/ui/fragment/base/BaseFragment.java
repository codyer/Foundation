package com.chinaredstar.longguo.ui.fragment.base;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinaredstar.foundation.common.utils.LogUtil;
import com.chinaredstar.foundation.presenter.impl.Presenter;
import com.chinaredstar.foundation.ui.fragment.FoundationFragment;
import com.chinaredstar.foundation.ui.viewmodel.ViewModel;

import org.antlr.v4.runtime.misc.NotNull;

public abstract class BaseFragment<P extends Presenter,VM extends ViewModel,B extends ViewDataBinding> extends FoundationFragment<VM,B> implements View.OnClickListener{

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**
         * 绑定view
         */
        if (!isBound()) {
            B b = DataBindingUtil.inflate(inflater,getLayoutID(), container, false);
            setBinding(b);
            setViewModel(createViewModel());
            mPresenter = createPresenter();
        }
        return getBinding().getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter.attachView(this);
    }

    @Override
    public void onDetach() {
        mPresenter.detachView();
        super.onDetach();
    }

    @Override
    public void showLoading(String msg) {
        LogUtil.d("BaseFragment ++ showLoading");
    }

    @Override
    public void hideLoading() {
        LogUtil.d("BaseFragment ++ hideLoading");
    }

    @Override
    public void showError(String msg) {
        LogUtil.d("BaseFragment ++ showError");
    }

    @Override
    public void showException(String msg) {
        LogUtil.d("BaseFragment ++ showException");
    }

    @Override
    public void showNetError() {
        LogUtil.d("BaseFragment ++ showNetError");
    }

    @Override
    public void onProgress(long count, long current) {
        LogUtil.d("BaseFragment ++ onProgress");
    }

    @Override
    public void onUpdate(Object tag, Object data) {
        LogUtil.d("BaseFragment ++ onUpdate");
    }
}
