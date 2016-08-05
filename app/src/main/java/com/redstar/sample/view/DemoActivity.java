package com.redstar.sample.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.redstar.foundation.R;
import com.redstar.foundation.databinding.DemoActivityBinding;
import com.redstar.foundation.view.BaseActivity;
import com.redstar.sample.viewmodel.impl.Demo3LayerViewModel;
import com.redstar.sample.viewmodel.impl.Demo4LayerViewModel;


/**
 * 主要进行生命周期管理以及绑定和属性设置
 */
public class DemoActivity extends BaseActivity {
    private Demo3LayerViewModel viewModel3;
    private Demo4LayerViewModel viewModel4;
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
        viewModel3 = new Demo3LayerViewModel(TAG);
        viewModel4 = new Demo4LayerViewModel(TAG);

        /**
         * ViewModel生效
         */
        binding.setVm3(viewModel3);
        binding.setVm4(viewModel4);


        viewModel3.onCreate();
        viewModel4.onCreate();
    }

    @Override
    protected void onDestroy() {
        viewModel3.onDestroy();
        viewModel4.onDestroy();
        super.onDestroy();
    }
}
