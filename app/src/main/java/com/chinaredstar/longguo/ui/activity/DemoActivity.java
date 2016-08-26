package com.chinaredstarer.longguo.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.chinaredstarer.longguo.R;
import com.chinaredstarer.longguo.databinding.DemoActivityBinding;
import com.chinaredstarer.longguo.presenter.impl.DemoPresenter;
import com.chinaredstarer.longguo.ui.activity.base.BaseActivity;
import com.chinaredstarer.longguo.ui.fragment.DemoFragment;
import com.chinaredstarer.longguo.ui.viewmodel.DemoViewModel;

/**
 * 主要进行生命周期管理以及绑定和属性设置
 */
public class DemoActivity extends BaseActivity<DemoPresenter,DemoViewModel, DemoActivityBinding> {

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
        return R.layout.demo_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * 将ViewModel和mBinding进行绑定
         */
        getBinding().setViewModel(getViewModel());
        getBinding().setListener(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.cancel(TAG);
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
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
