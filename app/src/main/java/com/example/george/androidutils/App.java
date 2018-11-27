package com.example.george.androidutils;

import android.app.Application;

/**
 * Created By George
 * Description:
 */
public class App extends Application {
    public static App app;
    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
    }
}
