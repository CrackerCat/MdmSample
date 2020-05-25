package com.spd.mdmsample.utils;

import android.util.Log;

import com.spd.mdmsample.BuildConfig;

/**
 * @author :Reginer in  2018/1/25 11:05.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
public class Logcat {
    private static final String TAG = "mdm";

    public static void d(String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
    }
}
