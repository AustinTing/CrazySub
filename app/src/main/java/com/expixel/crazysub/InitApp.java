package com.expixel.crazysub;

import android.app.Application;
import android.util.Log;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by cellbody on 2016/9/23.
 */

public class InitApp extends Application {
    public static final String TAG = "crazysub";

    @Override
    public void onCreate() {
        super.onCreate();
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()

                .cacheInMemory(true)
                // 先看會不會OOM 會的話在cach到記憶卡裡
                //                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);
        Log.i(TAG, this.getClass().getSimpleName()+": ImageLoader Init");


    }
}
