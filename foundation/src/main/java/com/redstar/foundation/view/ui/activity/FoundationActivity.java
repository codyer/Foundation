package com.redstar.foundation.view.ui.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.redstar.foundation.FoundationApplication;
import com.redstar.foundation.presenter.Presenter;
import com.redstar.foundation.view.viewmodel.ViewModel;

public abstract class FoundationActivity<V,P extends Presenter<V>,VM extends ViewModel, B extends ViewDataBinding> extends AppCompatActivity {
    /**
     * Log tag
     */
    public static String TAG = null;

    private VM mViewModel;
    private B mBinding;

    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    protected abstract P createPresenter();

    @Override
    protected void onResume() {
        super.onResume();
        FoundationApplication.getInstance().setCurrentActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        clearReferences();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearReferences();
        mPresenter.detachView();
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment, tag);
        fragmentTransaction.commit();
    }

    public <T extends Fragment> T getFragment(String tag) {
        return (T) getFragmentManager().findFragmentByTag(tag);
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

    private void clearReferences() {
        Activity currActivity = FoundationApplication.getInstance().getCurrentActivity();
        if (this.equals(currActivity)){
            FoundationApplication.getInstance().setCurrentActivity(null);
        }
    }
}
