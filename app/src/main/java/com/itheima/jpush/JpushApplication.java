package com.itheima.jpush;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/10 0:12
 * 描述： TODO
 */
public class JpushApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
