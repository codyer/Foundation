package com.redstar.foundation.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.redstar.foundation.BaseApplication;
import com.redstar.foundation.common.Constant;

import java.util.List;

/**
 * Created by Zhou Yang on 2016/7/26.
 */
public class AppUtil {

    //获取当前版本号
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(Constant.PKG_NAME, 0);
            versionName = packageInfo.versionName;
            if (TextUtils.isEmpty(versionName)) {
                return "No Version";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static String getDeviceId() {
        String deviceId = ((TelephonyManager) BaseApplication.getContext().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        return StringUtil.isEmpty(deviceId)?"no Device id":deviceId;
    }


    public static String getSessionId() {
        String sessionId = PreferencesUtil.getInstance().getPreferenceData(BaseApplication.getContext(), "sessionId");
        return StringUtil.isEmpty(sessionId)?"no Session Id":sessionId;
    }

    public static String getTimeStamp() {
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        return StringUtil.isEmpty(timeStamp)?"timeStamp":timeStamp;
    }

    //判断是不是到后台
    private boolean isAppOnForeground() {
        ActivityManager activityManager = (ActivityManager) BaseApplication.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = Constant.PKG_NAME;

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }

        return false;
    }

}

