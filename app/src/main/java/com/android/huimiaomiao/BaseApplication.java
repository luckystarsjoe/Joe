package com.android.huimiaomiao;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by caihaobing on 2017/5/27.
 * email：1215955877@qq.com
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
