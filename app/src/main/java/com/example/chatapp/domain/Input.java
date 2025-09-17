package com.example.chatapp.domain;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.widget.EditText;

import com.example.chatapp.model.InputView;


public class Input extends EditText {
    private GradientDrawable bg;

    public GradientDrawable getBg(){
        return bg;
    }

    public Input(Context context, int bgColor, float radius, int strokeColor, int strokeWidth){
        super(context);
        createInput(bgColor, radius, strokeColor, strokeWidth);
    }
    private void createInput(int bgColor, float radius, int strokeColor, int strokeWidth){

        bg = new GradientDrawable();
        bg.setCornerRadius(radius);
        bg.setStroke(strokeWidth, strokeColor);
        bg.setColor(bgColor);

        setBackground(bg);
    }
}
