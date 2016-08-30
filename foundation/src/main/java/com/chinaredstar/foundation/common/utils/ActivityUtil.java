package com.chinaredstar.foundation.common.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.chinaredstar.foundation.FoundationApplication;

/**
 * Created by cody.yi on 2016.8.15
 * Activity Navigator
 */
public class ActivityUtil {

    /**
     * startActivity
     *
     * @param clazz
     */
    public static void navigateTo(Class<? extends Activity> clazz) {
        Intent intent = new Intent(FoundationApplication.getInstance().getCurrentActivity(), clazz);
        FoundationApplication.getInstance().getCurrentActivity().startActivity(intent);
    }

    /**
     * startActivity with bundle
     *
     * @param clazz
     * @param bundle
     */
    public static void navigateTo(Class<? extends Activity> clazz, Bundle bundle) {
        Intent intent = new Intent(FoundationApplication.getInstance().getCurrentActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        FoundationApplication.getInstance().getCurrentActivity().startActivity(intent);
    }

    /**
     * startActivity then finish
     *
     * @param clazz
     */
    public static void navigateToThenKill(Class<? extends Activity> clazz) {
        Intent intent = new Intent(FoundationApplication.getInstance().getCurrentActivity(), clazz);
        FoundationApplication.getInstance().getCurrentActivity().startActivity(intent);
        finish();
    }

    /**
     * startActivity with bundle then finish
     *
     * @param clazz
     * @param bundle
     */
    public static void navigateToThenKill(Class<? extends Activity> clazz, Bundle bundle) {
        Intent intent = new Intent(FoundationApplication.getInstance().getCurrentActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        FoundationApplication.getInstance().getCurrentActivity().startActivity(intent);
        finish();
    }

    /**
     * startActivityForResult
     *
     * @param clazz
     * @param requestCode
     */
    public static void navigateToForResult(Class<? extends Activity> clazz, int requestCode) {
        Intent intent = new Intent(FoundationApplication.getInstance().getCurrentActivity(), clazz);
        FoundationApplication.getInstance().getCurrentActivity().startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz
     * @param requestCode
     * @param bundle
     */
    public static void navigateToForResult(Class<? extends Activity> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(FoundationApplication.getInstance().getCurrentActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        FoundationApplication.getInstance().getCurrentActivity().startActivityForResult(intent, requestCode);
    }

    public static void finish() {
        FoundationApplication.getInstance().getCurrentActivity().finish();
    }
}
