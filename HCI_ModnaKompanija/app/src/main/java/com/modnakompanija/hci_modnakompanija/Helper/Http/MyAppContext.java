package com.modnakompanija.hci_modnakompanija.Helper.Http;

import android.app.Application;
import android.content.Context;


public class MyAppContext extends Application {
    private static Context context;

    public static Context getContext(){return context;}
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }
}
