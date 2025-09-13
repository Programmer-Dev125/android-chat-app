package com.example.chatapp.domain;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;


import java.io.File;

public class Text {
    public static TextView getTextScreeen(Context context, String str, int size){
        TextView text = new TextView(context);
        Typeface styles = Typeface.createFromAsset(context.getAssets(), "fonts/Rubik-Regular.ttf");

        text.setTypeface(styles);
        text.setText(str);
        text.setTextSize(size);

        return text;
    }
}
