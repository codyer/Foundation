package com.redstar.longguo.ui.view.activity;

import android.os.Bundle;
import android.view.View;

import com.redstar.longguo.R;
import com.redstar.longguo.databinding.DemoActivityBinding;
import com.redstar.longguo.presenter.impl.DemoPresenter;
import com.redstar.longguo.ui.view.activity.base.BaseActivity;
import com.redstar.longguo.ui.viewmodel.DemoViewModel;

/**
 * 主要进行生命周期管理以及绑定和属性设置
 */
public class DemoActivity extends BaseActivity<DemoPresenter,DemoViewModel, DemoActivityBinding> {

    @Override
    protected DemoPresenter createPresenter() {
        return new DemoPresenter();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.demo_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * 将ViewModel和mBinding进行绑定
         */
        DemoViewModel vm = new DemoViewModel();
        vm.setInfo("default");
        vm.setTitle("default title");
        getBinding().setVm(vm);
        getBinding().setHandler(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.cancel(TAG);
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        mPresenter.onGetDemoClick(TAG + view.getTag());
    }
}
