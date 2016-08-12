package com.redstar.foundation.viewmodel;

import android.content.Context;

/**
 * Created by cody.yi on 2016/8/4.
 */
public class ViewModel implements ViewListener {
    public Object mTag = "TAG";
    public Context mContext;

    public ViewModel(Context context) {
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
