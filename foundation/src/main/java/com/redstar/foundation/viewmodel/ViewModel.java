package com.redstar.foundation.viewmodel;

import com.redstar.foundation.view.FoundationActivity;

/**
 * Created by cody.yi on 2016/8/4.
 */
public class ViewModel implements ViewLifeCycle {
    public Object mTag = "TAG";
    public FoundationActivity mContext;

    public ViewModel(FoundationActivity context) {
        mContext = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }
}
