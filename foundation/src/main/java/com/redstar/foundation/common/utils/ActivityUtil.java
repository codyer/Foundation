package com.redstar.foundation.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.redstar.foundation.FoundationApplication;

/**
 * Created by cody.yi on 2016.8.15
 * Activity Navigator
 */
public class ActivityUtil {

    /**
     * @param targetActivity
     */
    public static void navigateTo(@NonNull Class<? extends Activity> targetActivity) {
        navigateTo(targetActivity, new Intent(FoundationApplication.getInstance().getCurrentActivity(), targetActivity));
    }

    /**
     * @param targetActivity
     * @param intent
     */
    public static void navigateTo(@NonNull Class<? extends Activity> targetActivity, @NonNull Intent intent) {
        Activity currentActivity = FoundationApplication.getInstance().getCurrentActivity();
        navigateTo(currentActivity, targetActivity, intent);
    }

    /**
     * Used in onCreate(before onResume) method to ensure current activity is not null.
     *
     * @param context
     * @param targetActivity
     * @param intent
     */
    public static void navigateTo(@NonNull Context context, @NonNull Class<? extends Activity> targetActivity, @NonNull Intent intent) {
        context.startActivity(intent);
    }

    public static void finish() {
        FoundationApplication.getInstance().getCurrentActivity().finish();
    }
}
