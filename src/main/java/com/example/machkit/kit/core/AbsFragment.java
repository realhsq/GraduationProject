package com.example.machkit.kit.core;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.view.View.NO_ID;

public abstract class AbsFragment extends Fragment {

    private View contentCacheView;

    public abstract View onCreateContent(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle state);

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (contentCacheView != null) {
            ViewGroup contentParent = (ViewGroup) contentCacheView.getParent();
            if (contentParent != null) {
                contentParent.removeView(contentCacheView);
            }
            return contentCacheView;
        } else {
            contentCacheView = onCreateContent(inflater, container, savedInstanceState);
            return contentCacheView;
        }

    }

    public final <T extends View> T findViewById(int id) {
        if (id == NO_ID || contentCacheView == null) {
            return null;
        }
        return contentCacheView.findViewById(id);
    }


}
