package com.redstar.longguo.ui.viewmodel;

import com.redstar.foundation.ui.viewmodel.ViewModel;

/**
 * Created by cody.yi on 2016/8/24.
 * 例程
 */
public class DemoViewModel extends ViewModel {
    private String mTitle;//标题
    private String mInfo;//信息

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getInfo() {
        return mInfo;
    }

    public void setInfo(String info) {
        mInfo = info;
    }
}
