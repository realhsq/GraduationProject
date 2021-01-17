package com.example.machkit.kit.core;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

public abstract class AbsMachKitView implements MachKitView{

    private FrameLayout mRootView;
    private View mChildView;
    private WindowManager.LayoutParams mWindowLayoutParams;
    private String mTag = this.getClass().getSimpleName();

    public String getTag() {
        return mTag;
    }

    public WindowManager.LayoutParams getWindowLayoutParams() {
        return mWindowLayoutParams;
    }

    public FrameLayout getRootView() {
        return mRootView;
    }

    void performCreate(Context context) {
        mRootView = new FrameLayout(context);
        mChildView = onCreateView(context, mRootView);
        mRootView.addView(mChildView);
        mWindowLayoutParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //android 8.0
            mWindowLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            mWindowLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        mWindowLayoutParams.format = PixelFormat.TRANSPARENT;
        mWindowLayoutParams.flags= WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE ;
        mWindowLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        mWindowLayoutParams.width = 174;
        mWindowLayoutParams.height = 174;
        mWindowLayoutParams.x = 100;
        mWindowLayoutParams.y = 100;
    }



}
