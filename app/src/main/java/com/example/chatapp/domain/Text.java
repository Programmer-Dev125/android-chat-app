package com.example.chatapp.domain;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;


import java.io.File;

public class Text extends TextView {

    public Text(Context context, String str, int size){
        super(context);
        createText(context, str, size);
    }
    private void createText(Context context, String str, int size){
        Typeface styles = Typeface.createFromAsset(context.getAssets(), "fonts/Rubik-Regular.ttf");
        setTypeface(styles);
        setText(str);
        setTextSize(size);
    }
}
