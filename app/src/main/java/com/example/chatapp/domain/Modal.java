package com.example.chatapp.domain;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class Modal {
    public static FrameLayout createModal(Context context, LinearLayout content, float[] radius, int position){
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
        content.setLayoutParams(params);

        // background
        root.setBackgroundColor(Color.argb(128, 0, 0, 0));

        GradientDrawable bg = new GradientDrawable();
        bg.setCornerRadii(radius);
        bg.setColor(Color.WHITE);

        content.setBackground(bg);
        content.setPadding(20, 20, 20, 20);

        content.setOnClickListener((contentView)->{});
        root.addView(content);
        return root;
    }
}
