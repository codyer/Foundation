package com.chinaredstarer.longguo.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.chinaredstarer.foundation.ui.widget.pickerview.TimePickerView;
import com.chinaredstarer.longguo.R;
import com.chinaredstarer.longguo.databinding.DemoFragmentBinding;
import com.chinaredstarer.longguo.presenter.impl.DemoPresenter;
import com.chinaredstarer.longguo.ui.fragment.base.BaseFragment;
import com.chinaredstarer.longguo.ui.viewmodel.DemoViewModel;

import java.util.Calendar;
import java.util.Date;

public class DemoFragment extends BaseFragment<DemoPresenter, DemoViewModel, DemoFragmentBinding> {

    TimePickerView pvTime1;

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

        // picker
        pvTime1 = new TimePickerView(getActivity(), TimePickerView.Type.MONTH);
        pvTime1.setTime(new Date());
        pvTime1.setCyclic(false);
        pvTime1.setCancelable(true);
        //时间选择后回调
        pvTime1.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                Calendar calendar = Calendar.getInstance();
                if (date == null)
                    calendar.setTimeInMillis(System.currentTimeMillis());
                else
                    calendar.setTime(date);

                getBinding().time.setText(1+calendar.get(Calendar.MONTH)+"月");
            }
        });
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
            case R.id.time:
                pvTime1.show();
                break;
        }
    }
}
