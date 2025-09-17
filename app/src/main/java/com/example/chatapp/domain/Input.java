package com.example.chatapp.domain;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.widget.EditText;

import com.example.chatapp.model.InputView;


public class Input extends EditText {
    private final InputView InputWatcher = new InputView();

    public Input(Context context, int bgColor, float radius, int strokeColor, int strokeWidth, int focusStrokeColor){
        super(context);
        createInput(bgColor, radius, strokeColor, strokeWidth, focusStrokeColor);
    }
    private void createInput(int bgColor, float radius, int strokeColor, int strokeWidth, int focusStrokeColor){

        GradientDrawable bg = new GradientDrawable();
        bg.setCornerRadius(radius);
        bg.setStroke(strokeWidth, strokeColor);
        bg.setColor(bgColor);

        setBackground(bg);

        setOnFocusChangeListener((inputView, hasFocus)->{
            InputWatcher.focusStroke(bg, strokeColor, strokeWidth, hasFocus, focusStrokeColor);
        });
    }
}
