package com.example.machkit.kit.sysinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.machkit.kit.core.AbsFragment;
import com.example.machkitdevdemo.R;

public class SysInfoFragment extends AbsFragment {
    @Override
    public View onCreateContent(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle state) {
        return inflater.inflate(R.layout.fragment_sysinfo, container,false);
    }
}
