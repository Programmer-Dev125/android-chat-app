package com.example.chatapp.domain;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Insets;
import android.view.DisplayCutout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.os.Build;
import android.widget.ScrollView;

public class Screen {
    public static FrameLayout createScreen(Context context, View layout, ScreenCallback callback){
        FrameLayout root = new FrameLayout(context);
        ScrollView scrollView = new ScrollView(context);

        FrameLayout.LayoutParams scrollParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

            root.setOnApplyWindowInsetsListener((rootView, windowInsets) ->{

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

                scrollParams.setMargins(left, top, right, bottom);
                return windowInsets;
            });

        scrollView.setBackgroundColor(Color.WHITE);
        scrollView.setFillViewport(true);
        scrollView.addView(layout);

        if(callback != null){
            callback.toggleInput(scrollView);
        }

        root.addView(scrollView, scrollParams);
        return root;
    }
}
