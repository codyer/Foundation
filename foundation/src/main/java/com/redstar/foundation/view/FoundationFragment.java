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

    private VM viewModel;
    private B binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        setRetainInstance(true);
        if (viewModel != null)viewModel.onCreate();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (viewModel != null)viewModel.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (viewModel != null)viewModel.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (viewModel != null)viewModel.onDestroy();
    }

    public VM getViewModel() {
        if (viewModel == null) {
            throw new NullPointerException("You should setViewModel first!");
        }
        return viewModel;
    }

    public void setViewModel(@NonNull VM viewModel) {
        this.viewModel = viewModel;
    }

    public B getBinding() {
        if (binding == null) {
            throw new NullPointerException("You should setBinding first!");
        }
        return binding;
    }

    public void setBinding(@NonNull B binding) {
        this.binding = binding;
    }

}
