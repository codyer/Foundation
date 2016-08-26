package com.chinaredstar.foundation;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by cody.yi on 2016/7/12.
 */
public class FoundationApplication extends Application {
    private static FoundationApplication mInstance;

    private Activity mCurrentActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static Context getContext() {
        return mInstance.getApplicationContext();
    }

    public static FoundationApplication getInstance() {
        return mInstance;
    }

    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    public void setCurrentActivity(@NonNull Activity mCurrentActivity) {
        this.mCurrentActivity = mCurrentActivity;
    }
}
