package com.example.chatapp.ui;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.chatapp.domain.Screen;
import com.example.chatapp.domain.Text;

public class Landing extends Screen {

    public Landing(Context context){
        super(context);
    }
    @Override
    protected FrameLayout createContent(Context context){
          Text text = new Text(context, "This is a text", 20);
          addView(text);
          return this;
    }
}
