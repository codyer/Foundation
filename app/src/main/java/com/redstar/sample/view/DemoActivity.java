package com.redstar.sample.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.redstar.foundation.R;
import com.redstar.foundation.databinding.DemoActivityBinding;
import com.redstar.foundation.view.BaseActivity;
import com.redstar.sample.viewmodel.impl.Demo3LayerViewModel;


/**
 * 主要进行生命周期管理以及绑定和属性设置
 */
public class DemoActivity extends BaseActivity {
    private Demo3LayerViewModel viewModel;
    private DemoActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 绑定view
         */
        binding = DataBindingUtil.setContentView(
                this, R.layout.demo_activity);

        /**
         * 创建ViewModel
         */
        viewModel = new Demo3LayerViewModel(TAG);

        /**
         * ViewModel生效
         */
        binding.setVm(viewModel);


        viewModel.onCreate();
    }

    @Override
    protected void onDestroy() {
        viewModel.onDestroy();
        super.onDestroy();
    }
}
