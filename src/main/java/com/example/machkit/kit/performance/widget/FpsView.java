package com.example.machkit.kit.performance.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.machkitdevdemo.R;

public class FpsView extends RelativeLayout {

    private TextView mTextView;

    public FpsView(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.mk_fps_info, this, true);
        mTextView = view.findViewById(R.id.tv_fps_info);
    }

    public void setText(String text) {
        mTextView.setText(text);
    }

}
