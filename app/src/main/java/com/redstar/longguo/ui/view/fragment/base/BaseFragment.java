package com.redstar.longguo.ui.view.fragment.base;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redstar.foundation.presenter.impl.Presenter;
import com.redstar.foundation.ui.view.EventHandler;
import com.redstar.foundation.ui.view.fragment.FoundationFragment;
import com.redstar.foundation.ui.viewmodel.ViewModel;

import org.antlr.v4.runtime.misc.NotNull;

public abstract class BaseFragment<P extends Presenter,VM extends ViewModel,B extends ViewDataBinding> extends FoundationFragment<VM,B> implements EventHandler{

    protected P mPresenter;
    protected abstract@NotNull P createPresenter();
    private B b;
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
        if (b == null) {
            b = DataBindingUtil.inflate(inflater,getLayoutID(), container, false);
            setBinding(b);
            mPresenter = createPresenter();
        }
        return getBinding().getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter.attachView(getActivity());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.detachView();
    }
}
