package com.redstar.longguo.ui.viewmodel;

import android.databinding.ObservableField;

import com.redstar.foundation.ui.viewmodel.ViewModel;

/**
 * Created by cody.yi on 2016/8/24.
 * 例程 界面运行期间值会有变更时使用 observable 成员变量
 * 界面初始化后就不再变更的可以使用非 observable 成员变量
 * 尽量少使用 observable 成员变量
 */
public class DemoViewModel extends ViewModel {

    private final ObservableField<String> mTitle = new ObservableField<>();//标题
    private final ObservableField<String> mInfo = new ObservableField<>();//信息

    public ObservableField<String> getTitle() {
        return mTitle;
    }

    public ObservableField<String> getInfo() {
        return mInfo;
    }

    public void setTitle(String title) {
        mTitle.set(title);
    }

    public void setInfo(String info) {
        mInfo.set(info);
    }
}
