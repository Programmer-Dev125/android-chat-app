package com.example.chatapp.domain;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class Modal {
    public static FrameLayout createModal(Context context,  float[] radius, int position){
        FrameLayout root = new FrameLayout(context);

        // Params
        FrameLayout.LayoutParams rootParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        // ContentParams
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                position
        );

        root.setLayoutParams(rootParams);

        // background
        root.setBackgroundColor(Color.argb(128, 0, 0, 0));

        GradientDrawable bg = new GradientDrawable();
        bg.setCornerRadii(radius);
        bg.setColor(Color.WHITE);

        root.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {

            @Override
            public void onChildViewAdded(View parent, View child) {
                child.setLayoutParams(params);
                child.setBackground(bg);
                child.setPadding(20, 20, 20, 20);
            }

            @Override
            public void onChildViewRemoved(View parent, View child) {}
        });

        return root;
    }
}
