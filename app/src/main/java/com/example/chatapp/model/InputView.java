package com.example.chatapp.model;

import android.graphics.drawable.GradientDrawable;

public final class InputView {
    public void focusStroke(GradientDrawable drawable, int strokeColor, int strokeWidth, boolean focus, int focusStrokeColor){
        System.out.println(focus);
        drawable.setStroke(strokeWidth, focus ? focusStrokeColor : strokeColor);
    }
}
