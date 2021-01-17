package com.example.machkit.kit.performance;

import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;

public class PerformanceDataManager {

    private static final String TAG = "PerformanceDataManager";
    private int FPS_SAMPLING_TIME = 1000;
    private int mLastFpsSampleData;
    private

    private FrameRateRunnable mRateWorker = new FrameRateRunnable();

    private Handler mMainHandler = new Handler(Looper.getMainLooper());

    private static class Holder{
        public static PerformanceDataManager instance = new PerformanceDataManager();
    }

    public static PerformanceDataManager getInstance() {
        return Holder.instance;
    }

    public void startMonitorFPS() {
        mMainHandler.postDelayed(mRateWorker, FPS_SAMPLING_TIME);
        Choreographer.getInstance().postFrameCallback(mRateWorker);
    }

    public void stopMonitorFPS() {
        mMainHandler.removeCallbacks(mRateWorker);
        Choreographer.getInstance().removeFrameCallback(mRateWorker);
    }

    private class FrameRateRunnable implements Runnable, Choreographer.FrameCallback{
        private int totalFramesPerSecond;

        @Override
        public void run() {
            mLastFpsSampleData = totalFramesPerSecond;
            totalFramesPerSecond = 0;
            mMainHandler.postDelayed(this, FPS_SAMPLING_TIME);
        }

        @Override
        public void doFrame(long frameTimeNanos) {
            totalFramesPerSecond ++;
            Choreographer.getInstance().postFrameCallback(this);
        }
    }






}
