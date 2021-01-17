package com.example.machkitdevdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.machkit.MachKit;

public class MainActivity extends AppCompatActivity {

    private boolean enabled = false;
    private Button btn_enable_debug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initViews();
        MachKit.getIns().init(getApplication());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + getPackageName()));
    }

    private void findViews() {
        btn_enable_debug = findViewById(R.id.btn_enable_machkit);
    }

    private void initViews() {
        btn_enable_debug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enabled = !enabled;
                if(enabled) {
                    btn_enable_debug.setText("disable machkit");
                    MachKit.getIns().show();
                } else {
                    btn_enable_debug.setText("enable machkit");
                    MachKit.getIns().hide();
                }
            }
        });
    }

}