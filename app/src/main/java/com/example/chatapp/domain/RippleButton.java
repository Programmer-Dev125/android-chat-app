package com.example.chatapp.domain;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.chatapp.model.ButtonView;

public class RippleButton extends LinearLayout {

    public RippleButton(Context context, View content, int color){
        super(context);
        createRippleButton(content, color);
    }
    public void createRippleButton(View content, int color){

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        setLayoutParams(layoutParams);
        setOrientation(LinearLayout.HORIZONTAL);

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

        setBackground(rippleBg);
        setPadding(0, 15, 0, 15);
        addView(content);
    }
}
