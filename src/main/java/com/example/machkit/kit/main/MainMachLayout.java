package com.example.machkit.kit.main;

import android.content.Context;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class MainMachLayout extends FrameLayout {

    private MainIconView mMainIconView;
    private float mMoveX, mMoveY, mMoveStartX, mMoveStartY;
    private boolean mIsSelfTouchEnd = true;
    private List<View> attachedViews;

    public MainMachLayout(@NonNull Context context) {
        super(context);
        init(context);
        attachedViews = new ArrayList<>();
    }

    public MainIconView getMainIconView() {
        return mMainIconView;
    }

    private void init(Context context) {
        mMainIconView = new MainIconView(context);
        attachedViews.add(mMainIconView);
        addView(mMainIconView, generateInitLayoutParams());
    }

    private FrameLayout.LayoutParams generateCommonLayoutParams() {
        return new FrameLayout.LayoutParams(mMainIconView.width, mMainIconView.height);
    }

    private FrameLayout.LayoutParams generateInitLayoutParams() {
        FrameLayout.LayoutParams lp = generateCommonLayoutParams();
        lp.gravity = Gravity.BOTTOM;
        return lp;
    }

    private boolean isViewTouched(View view, float touchX, float touchY) {
        if(view == null) return false;
        int[] coordinates = new int[2];
        view.getLocationOnScreen(coordinates);
        int startX = coordinates[0];
        int startY = coordinates[1];
        int endX = view.getMeasuredWidth() + startX;
        int endY = view.getMeasuredHeight() + startY;
        return !(touchY < startY || touchY > endY || touchX < startX || touchX > endX);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                return true;
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(!isViewTouched(mMainIconView, event.getRawX(), event.getRawY())) {
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:
                mIsSelfTouchEnd = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if(mIsSelfTouchEnd) {
                    mMoveStartX = mMainIconView.getX();
                    mMoveStartY = mMainIconView.getY();
                    mMoveX = x;
                    mMoveY = y;
                    mIsSelfTouchEnd = false;
                }
                float currentX = x - mMoveX + mMoveStartX;
                float currentY = y - mMoveY + mMoveStartY;
                mMainIconView.setX(currentX);
                mMainIconView.setY(currentY);
                break;
            default:
                break;
        }
        return true;
    }
}
