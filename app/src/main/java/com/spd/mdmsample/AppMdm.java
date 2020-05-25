package com.spd.mdmsample;

import android.app.Application;


/**
 * 继承Application
 *
 * @author TER
 * @date 2018/1/25
 */

public class AppMdm extends Application {

    private static AppMdm sInstance;
    public AppMdm instance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        instance = this;
    }

    public static AppMdm getInstance() {
        return sInstance;
    }

}
