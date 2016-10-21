package com.expixel.crazysub;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;


/**
 * Created by cellbody on 2016/9/23.
 */

public class InitApp extends Application {
    public static final String TAG = "crazysub";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, this.getClass().getSimpleName()+": ImageLoader Init");

    }
}
