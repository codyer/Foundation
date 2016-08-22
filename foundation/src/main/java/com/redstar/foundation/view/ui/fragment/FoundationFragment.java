package com.redstar.foundation.view.ui.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.redstar.foundation.presenter.Presenter;
import com.redstar.foundation.view.viewmodel.ViewModel;

public abstract class FoundationFragment<V,P extends Presenter<V>,VM extends ViewModel, B extends ViewDataBinding>  extends Fragment {
    /**
     * Log tag
     */
    public static String TAG = null;

    private VM mViewModel;
    private B mBinding;

    protected P mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        setRetainInstance(true);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    protected abstract P createPresenter();

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    public VM getViewModel() {
        if (mViewModel == null) {
            throw new NullPointerException("You should setViewModel first!");
        }
        return mViewModel;
    }

    public void setViewModel(@NonNull VM viewModel) {
        this.mViewModel = viewModel;
    }

    public B getBinding() {
        if (mBinding == null) {
            throw new NullPointerException("You should setBinding first!");
        }
        return mBinding;
    }

    public void setBinding(@NonNull B binding) {
        this.mBinding = binding;
    }

}
