package com.example.chatapp.domain;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class Card extends FrameLayout {
    public Card(Context context, int width, float[] radius, View content, boolean shadow, int position){
        super(context);
        createCard(width, radius, content, shadow, position);
    }
    private void createCard(int width, float[] radius, View content, boolean shadow, int position){

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.WHITE);
        bg.setCornerRadii(radius);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                width,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                position
        );

        setLayoutParams(params);
        setBackground(bg);
        if(shadow){
            setElevation(20);
        }

        setPadding(20, 20, 20, 20);
        addView(content);
    }
}
