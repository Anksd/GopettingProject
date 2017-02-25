package com.rson.brothers.gopettingproject;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;

/**
 * Created by Brother on 2/24/2017.
 */
public class MyApplication extends Application {

    private  static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication=this;
    }

    public static MyApplication getMyApplication() {
        return myApplication;
    }

   public static Context getAppContext(){
       return myApplication.getApplicationContext();
   }
}
