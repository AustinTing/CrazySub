package com.expixel.crazysub;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

//                GET KEY HASH
                try{
                    PackageInfo info = getPackageManager().getPackageInfo("com.expixel.crazysub", PackageManager.GET_SIGNATURES);
                    for(Signature signature : info.signatures)
                    {      MessageDigest md;
                        md =MessageDigest.getInstance("SHA");
                        md.update(signature.toByteArray());
                        //String something = new String(Base64.encodeBytes(md.digest()));
                        String KeyResult =new String(Base64.encode(md.digest(), 0));
                        Log.d("crazysub", "KeyHash :"+KeyResult);

                    }
                }catch(PackageManager.NameNotFoundException e1){
                    Log.e("name not found", e1.toString());
                }catch(NoSuchAlgorithmException e){
                    Log.e("no such an algorithm", e.toString());
                }catch(Exception e){
                    Log.e("exception", e.toString());
                }


    }
}
