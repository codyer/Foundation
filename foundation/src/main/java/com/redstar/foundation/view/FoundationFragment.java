package com.redstar.foundation.view;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.redstar.foundation.viewmodel.ViewModel;

public class FoundationFragment<VM extends ViewModel, B extends ViewDataBinding>  extends Fragment {
    /**
     * Log tag
     */
    public static String TAG = null;

    private VM mViewModel;
    private B mBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        setRetainInstance(true);
        if (mViewModel != null) mViewModel.onCreate();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mViewModel != null) mViewModel.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mViewModel != null) mViewModel.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mViewModel != null) mViewModel.onDestroy();
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
