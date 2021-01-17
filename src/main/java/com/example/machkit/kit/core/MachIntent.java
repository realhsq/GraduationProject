package com.example.machkit.kit.core;

import android.app.Activity;

import com.example.machkit.MachKit;

public class MachIntent {

    public Activity activity;

    private String tag;

    public Class<? extends AbsMachKitView> target;

    public MachIntent(Class targetClass) {
        this.target = targetClass;
        this.tag = targetClass.getSimpleName();
    }
}
