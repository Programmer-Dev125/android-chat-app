package com.example.chatapp.domain;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Insets;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.DisplayCutout;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class Modal {
    private static Boolean hasShown = false;
    public static FrameLayout createModal(Context context, LinearLayout content, float[] radius, int position){
        FrameLayout root = new FrameLayout(context);

        // screen dimensions
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        int screenHeight = context.getResources().getDisplayMetrics().heightPixels;

        // Params
        FrameLayout.LayoutParams rootParams = new FrameLayout.LayoutParams(
                screenWidth,
                screenHeight
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

        root.setOnApplyWindowInsetsListener((view, windowInsets)->{
            if(!windowInsets.hasInsets()){
                return windowInsets;
            }
            int top, bottom, left, right;

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                Insets insets = windowInsets.getInsets(WindowInsets.Type.systemBars());
                DisplayCutout disp = windowInsets.getDisplayCutout();

                top = insets.top;
                bottom = insets.bottom;
                left = insets.left;
                right = insets.right;

                if(disp != null){
                    top = Math.max(top, disp.getSafeInsetTop());
                    bottom = Math.max(bottom, disp.getSafeInsetBottom());
                    left = Math.max(left, disp.getSafeInsetLeft());
                    right = Math.max(right, disp.getSafeInsetRight());
                }
            }else{
                top = windowInsets.getStableInsetTop();
                bottom = windowInsets.getStableInsetBottom();
                left = windowInsets.getStableInsetLeft();
                right = windowInsets.getStableInsetRight();
            }

            params.setMargins(left, top, right, bottom);
            return windowInsets;
        });

        content.setOnClickListener((contentView)->{});
        root.addView(content);
        return root;
    }
}
