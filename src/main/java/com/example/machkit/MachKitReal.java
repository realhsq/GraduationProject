package com.example.machkit;

import android.app.Activity;
import android.app.Application;

import com.example.machkit.kit.core.MachIntent;
import com.example.machkit.kit.core.MainMachKitViewManager;
import com.example.machkit.kit.main.MainIconView;

public final class MachKitReal {

    private static final String TAG = "MachKitReal";
    private static Application mApplication;

    public static void init(Application application) {
        mApplication = application;
        MainMachKitViewManager.getIns().init(mApplication);
    }

    public static void show() {
        MachIntent intent = new MachIntent(MainIconView.class);
        MainMachKitViewManager.getIns().attach(intent);
    }

    public static void hide() {
        MainMachKitViewManager.getIns().detach();
    }




}
