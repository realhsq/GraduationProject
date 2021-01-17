package com.example.machkit.kit.performance.widget;

import com.example.machkit.kit.main.MainMachLayout;
import com.example.machkit.kit.performance.PerformanceDataManager;

public class PerformanceViewManager {

    private MainMachLayout mainLayout;
    private FpsView mFpsView;

    private static class Holder{
        public static PerformanceViewManager instance = new PerformanceViewManager();
    }

    public static PerformanceViewManager getInstance() {
        return Holder.instance;
    }

    public void attachFpsMonitor() {
        PerformanceDataManager.getInstance().startMonitorFPS();
    }
}
