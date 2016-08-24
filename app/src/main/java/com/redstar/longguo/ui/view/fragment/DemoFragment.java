package com.redstar.longguo.ui.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.redstar.longguo.R;
import com.redstar.longguo.databinding.DemoFragmentBinding;
import com.redstar.longguo.presenter.impl.DemoPresenter;
import com.redstar.longguo.ui.view.fragment.base.BaseFragment;
import com.redstar.longguo.ui.viewmodel.DemoViewModel;

public class DemoFragment extends BaseFragment<DemoPresenter, DemoViewModel, DemoFragmentBinding> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected DemoViewModel createViewModel() {
        DemoViewModel vm = new DemoViewModel();
        vm.setInfo("default");
        vm.setTitle("default title");
        return vm;
    }

    @Override
    protected DemoPresenter createPresenter() {
        return new DemoPresenter();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.demo_fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getBinding().setVm(getViewModel());
        getBinding().setHandler(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addFragment:
                Fragment fragment = new DemoFragment();
                addFragment(R.id.frame,fragment,TAG);
                break;
            case R.id.getDemo:
                mPresenter.onGetDemoClick(TAG + view.getTag());
                break;
        }
    }
}
