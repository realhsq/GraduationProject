package com.example.machkit;

import android.app.Activity;
import android.app.Application;

public class MachKit {

    private final static String TAG = "MachKit";
    private Application mApplication;

    private MachKit(){}

    private static class Holder{
        public static MachKit INSTANCE = new MachKit();
    }

    public static MachKit getIns(){
        return Holder.INSTANCE;
    }

    public Application getApplication() {
        return mApplication;
    }

    public void init(Application application) {
        mApplication = application;
        MachKitReal.init(mApplication);
    }

    public void show() {
        MachKitReal.show();
    }

    public void hide() {
        MachKitReal.hide();
    }

}
