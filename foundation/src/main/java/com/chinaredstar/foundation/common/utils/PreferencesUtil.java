package com.chinaredstar.foundation.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * Created by lenovo on 2016/7/11.
 */
public class PreferencesUtil {

    private static PreferencesUtil instance;

    private PreferencesUtil() {
    }

    public static PreferencesUtil getInstance() {
        if (instance == null) {
            instance = new PreferencesUtil();
        }
        return instance;
    }

    private final String APP_PREFERENCES = "orderpay_preferences";
    public static final String TOKEN = "token";
    public static final String MOBILE = "mobile";
    public static final String PASSWORD = "password";


    /*
     * 统一存入Preference文件方法
     */
    public boolean savePreferenceData(Context context, String key, String value) {
        boolean result = false;
        SharedPreferences.Editor editor = getSharedPrefrencesEditor(context);
        if (editor != null && (!TextUtils.isEmpty(key))) {
            editor.putString(key, value);
            result = editor.commit();
        }
        return result;
    }

    public String getPreferenceData(Context context, String key) {
        String value = null;
        if (!TextUtils.isEmpty(key)) {
            SharedPreferences sharedPre = getSharedPreferences(context);
            if (sharedPre != null) {
                value = sharedPre.getString(key, "");
            }
        }
        return value;
    }

    /**
     * 获取sharedPrefrences
     *
     * @param context
     * @return
     */
    public SharedPreferences getSharedPreferences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_PREFERENCES, Activity.MODE_PRIVATE);
        return preferences;
    }

    /**
     * 获取SharedPrefrencesEditor
     *
     * @param context
     * @return
     */
    public SharedPreferences.Editor getSharedPrefrencesEditor(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_PREFERENCES, Activity.MODE_PRIVATE);
        if (preferences != null) {
            return preferences.edit();
        }
        return null;
    }

}
