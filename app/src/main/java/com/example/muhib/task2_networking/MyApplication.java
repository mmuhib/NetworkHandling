package com.example.muhib.task2_networking;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    public static MyApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance=this;
    }
    public static Context getAppContext()
    {
        return sInstance.getApplicationContext();
    }
}
