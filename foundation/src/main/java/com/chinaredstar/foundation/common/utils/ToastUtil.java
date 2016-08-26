package com.chinaredstarer.foundation.common.utils;

import android.content.Context;
import android.widget.Toast;

import com.chinaredstar.foundation.FoundationApplication;

/**
 * Created by cody.yi on 2016/8/4.
 */
public class ToastUtil {
    public static void showToast(String text) {
        showToast(FoundationApplication.getContext(),text);
    }

    public static void showToast(int resId) {
        showToast(FoundationApplication.getContext(),resId);
    }

    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void showToast(Context context, int resId) {
        Toast.makeText(context, context.getString(resId), Toast.LENGTH_LONG)
                .show();
    }
}
