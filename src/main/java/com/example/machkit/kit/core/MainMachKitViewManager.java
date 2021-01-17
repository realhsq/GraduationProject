package com.example.machkit.kit.core;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.machkit.constant.FragmentConst;
import com.example.machkit.kit.main.MainMachLayout;
import com.example.machkit.kit.main.MainIconView;

public class MainMachKitViewManager implements Application.ActivityLifecycleCallbacks{

    private static final String TAG = "MainMachKitViewManager";

    private Application mContext;
    private MainMachLayout mMainMachLayout;
    private boolean shouldAttach = false;
    private boolean hasInit = false;
    private Activity currentActivity;

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        currentActivity = activity;
        tryAttachToWindow(activity);
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        detach();
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }

    private static class Holder{
        public static MainMachKitViewManager instance = new MainMachKitViewManager();
    }

    private MainMachKitViewManager(){}

    public static MainMachKitViewManager getIns() {
        return Holder.instance;
    }

    public void init(Application context) {
        mContext = context;
        mContext.registerActivityLifecycleCallbacks(this);
    }

    private void initViews() {
        mMainMachLayout = new MainMachLayout(mContext);
        initMainIcon();
        hasInit = true;
    }

    private void initMainIcon() {
        MainIconView mainIconView = mMainMachLayout.getMainIconView();
        mainIconView.setClickHandler(new MainIconView.OnClickHandler() {
            @Override
            public void onClick() {
                Intent intent = new Intent(mContext, GlobalActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(FragmentConst.FRAGMENT_INDEX, FragmentConst.SYSTEM_INFO);
                mContext.startActivity(intent);
            }
        });
    }

    public MainMachLayout getMainIconLayout() {
        if(!hasInit) {
            initViews();
        }
        return mMainMachLayout;
    }

    private void tryAttachToWindow(Activity activity) {
        if(!shouldAttach || activity instanceof GlobalActivity) return;
        final ViewGroup contentView = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        contentView.addView(getMainIconLayout(), lp);
    }

    public void attach(MachIntent intent) {
        shouldAttach = true;
        if(mMainMachLayout != null) {
            realDetach();
        }
        tryAttachToWindow(currentActivity);
    }

    public void detach() {
        shouldAttach = false;
        realDetach();
    }

    public void realDetach() {
        ViewParent pre = mMainMachLayout.getParent();
        if(pre != null) {
            ((ViewGroup) pre).removeView(mMainMachLayout);
        }
    }


}
