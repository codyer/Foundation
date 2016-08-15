package com.redstar.foundation.view;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.redstar.foundation.FoundationApplication;
import com.redstar.foundation.viewmodel.ViewModel;

public class FoundationActivity<VM extends ViewModel, B extends ViewDataBinding> extends AppCompatActivity {
    /**
     * Log tag
     */
    public static String TAG = null;

    private VM viewModel;
    private B binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        if (viewModel != null)viewModel.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (viewModel != null)viewModel.onResume();
        FoundationApplication.getInstance().setCurrentActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (viewModel != null)viewModel.onPause();
        clearReferences();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (viewModel != null)viewModel.onDestroy();
        clearReferences();
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

    private void clearReferences() {
        Activity currActivity = FoundationApplication.getInstance().getCurrentActivity();
        if (this.equals(currActivity)){
            FoundationApplication.getInstance().setCurrentActivity(null);
        }
    }
}
