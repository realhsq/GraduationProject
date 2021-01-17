package com.example.machkit.kit.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.example.machkitdevdemo.R;

public class MainIconView extends RelativeLayout {

    public interface OnClickHandler{
        void onClick();
    }

    public int width = 200;
    public int height = 200;
    private ImageView icon;
    private OnClickHandler clickHandler;

    public void setClickHandler(OnClickHandler handler) {
        clickHandler = handler;
    }

    public MainIconView(@NonNull Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.mk_main_icon, this, true);
        icon = view.findViewById(R.id.iv_machkit_icon);

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickHandler.onClick();
            }
        });
    }

}
