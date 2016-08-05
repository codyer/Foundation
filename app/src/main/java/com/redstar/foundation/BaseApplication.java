package com.redstar.foundation;

import android.app.Application;
import android.content.Context;

/**
 * Created by cody.yi on 2016/7/12.
 */
public class BaseApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }

}
