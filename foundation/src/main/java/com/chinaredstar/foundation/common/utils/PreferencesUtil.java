package com.chinaredstar.foundation.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.chinaredstar.foundation.FoundationApplication;

/**
 * Created by lenovo on 2016/7/11.
 *
 */
public class PreferencesUtil {

    private Context context;
    private SharedPreferences.Editor editor = null;
    public static String FILENAME = "share_prefers";
    private int mode = Context.MODE_PRIVATE;
    SharedPreferences sp;
    private static PreferencesUtil mSp = null;


    public static synchronized PreferencesUtil getInstance() {
        if (mSp == null) {
            mSp = new PreferencesUtil(FoundationApplication.getContext());
        }
        return mSp;
    }

    private PreferencesUtil(Context context) {
        this.context = context;
        this.sp = context.getSharedPreferences(FILENAME, mode);
        this.editor = sp.edit();
    }

    //Should be use other preference file.
    public void usePref(Context context, String name) {
        this.sp = context.getSharedPreferences(name, mode);
        this.editor = sp.edit();

    }

    // Boolean
    public void setValue(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void setValue(int resKey, boolean value) {
        setValue(this.context.getString(resKey), value);
    }

    // Float
    public void setValue(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    public void setValue(int resKey, float value) {
        setValue(this.context.getString(resKey), value);
    }

    // Integer
    public void setValue(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public void setValue(int resKey, int value) {
        setValue(this.context.getString(resKey), value);
    }

    // Long
    public void setValue(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }
    public void setValue(int resKey, long value) {
        setValue(this.context.getString(resKey), value);
    }

    // String
    public void setValue(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public void setValue(int resKey, String value) {
        setValue(this.context.getString(resKey), value);
    }

    // Get

    // Boolean
    public boolean getValue(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public boolean getValue(int resKey, boolean defaultValue) {
        return getValue(this.context.getString(resKey), defaultValue);
    }

    // Float
    public float getValue(String key, float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    public float getValue(int resKey, float defaultValue) {
        return getValue(this.context.getString(resKey), defaultValue);
    }

    // Integer
    public int getValue(String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    public int getValue(int resKey, int defaultValue) {
        return getValue(this.context.getString(resKey), defaultValue);
    }

    // Long
    public long getValue(String key, long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    public long getValue(int resKey, long defaultValue) {
        return getValue(this.context.getString(resKey), defaultValue);
    }

    // String
    public String getValue(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public String getValue(int resKey, String defaultValue) {
        return getValue(this.context.getString(resKey), defaultValue);
    }

    // Delete
    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }
    public void commit(){
        editor.commit();
    }
    public void clear() {
        editor.clear();
        editor.commit();
    }
}
