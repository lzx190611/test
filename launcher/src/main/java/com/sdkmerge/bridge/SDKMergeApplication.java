package com.sdkmerge.bridge;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.auto.service.AutoService;
import com.morechili.yuki.sdkmerge.IApplication;
import com.sdkmerge.combine.SDKMergeCombine;
import com.sdkmerge.demo.BuildConfig;

@AutoService(IApplication.class)
public class SDKMergeApplication implements IApplication {
    @Override
    public void onCreate(Application application) {
        Log.d("SDKMergeApplication", "onCreate: call init");
        SDKMergeCombine.getInstance().init(application, BuildConfig.class);
    }

    @Override
    public void attachBaseContext(Application application, Context context) {
        Log.d("SDKMergeApplication", "attachBaseContext");
    }
}
