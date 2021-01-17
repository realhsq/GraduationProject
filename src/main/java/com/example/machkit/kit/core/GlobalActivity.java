package com.example.machkit.kit.core;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.machkit.constant.FragmentConst;
import com.example.machkit.kit.sysinfo.SysInfoFragment;

public class GlobalActivity extends AppCompatActivity {
    private static final String TAG = "GlobalActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null) {
            finish();
            return;
        }

        Class<? extends AbsFragment> clazz = null;
        int index = bundle.getInt(FragmentConst.FRAGMENT_INDEX);
        switch (index) {
            case FragmentConst.SYSTEM_INFO:
                clazz = SysInfoFragment.class;
                break;
        }

        showContent(clazz);
    }

    private void showContent(Class<? extends AbsFragment> clazz) {
        if(clazz == null) {
            finish();
            return;
        }
        try{
            AbsFragment fragment = clazz.newInstance();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(android.R.id.content, fragment);
            //ft.addToBackStack("");
            ft.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
