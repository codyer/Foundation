package com.chinaredstarer.foundation.ui.activity;

import android.app.Activity;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.chinaredstar.foundation.FoundationApplication;
import com.chinaredstarer.foundation.ui.IView;
import com.chinaredstarer.foundation.ui.viewmodel.ViewModel;

public abstract class FoundationActivity<VM extends ViewModel, B extends ViewDataBinding> extends AppCompatActivity implements IView<VM, B> {
    /**
     * Log tag
     */
    public static String TAG = null;

    private VM mViewModel;
    private B mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    protected void onResume() {
        super.onResume();
        FoundationApplication.getInstance().setCurrentActivity(this);
    }

    @Override
    protected void onPause() {
        clearReferences();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        clearReferences();
        super.onDestroy();
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment, tag);
        fragmentTransaction.commit();
    }

    public <T extends Fragment> T getFragment(String tag) {
        return (T) getSupportFragmentManager().findFragmentByTag(tag);
    }

    @Override
    public VM getViewModel() {
        if (mViewModel == null) {
            throw new NullPointerException("You should setViewModel first!");
        }
        return mViewModel;
    }

    @Override
    public void setViewModel(@NonNull VM viewModel) {
        this.mViewModel = viewModel;
    }

    @Override
    public B getBinding() {
        if (mBinding == null) {
            throw new NullPointerException("You should setBinding first!");
        }
        return mBinding;
    }

    @Override
    public void setBinding(@NonNull B binding) {
        this.mBinding = binding;
    }

    /**
     * 是否已经设置bind
     */
    @Override
    public boolean isBound() {
        return mBinding != null;
    }
    private void clearReferences() {
        Activity currActivity = FoundationApplication.getInstance().getCurrentActivity();
        if (this.equals(currActivity)) {
            FoundationApplication.getInstance().setCurrentActivity(null);
        }
    }
}
