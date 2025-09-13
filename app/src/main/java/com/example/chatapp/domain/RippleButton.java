package com.example.chatapp.domain;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class RippleButton {
    public static View createRippleButton(Context context, View content, int color, ButtonCallback callback){
        LinearLayout layout = new LinearLayout(context);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layout.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        layout.setLayoutParams(layoutParams);

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(color);
        bg.setCornerRadius(20f);

        GradientDrawable mask = new GradientDrawable();
        mask.setColor(Color.WHITE);
        mask.setCornerRadius(20f);

        RippleDrawable rippleBg = new RippleDrawable(
                ColorStateList.valueOf(Color.argb(128, 0, 0, 0)),
                bg,
                mask
        );

        layout.setOnClickListener((layoutView)->{
            callback.onClick();
        });

        layout.setBackground(rippleBg);
        layout.addView(content);
        return layout;
    }
}
