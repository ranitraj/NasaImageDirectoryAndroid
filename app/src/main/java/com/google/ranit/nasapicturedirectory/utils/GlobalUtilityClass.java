package com.google.ranit.nasapicturedirectory.utils;

import android.app.Application;
import android.content.Context;

public class GlobalUtilityClass extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
