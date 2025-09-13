package com.example.chatapp.ui;

import android.content.Context;
import android.view.View;
import android.widget.HorizontalScrollView;

import java.util.List;

public class Carousel {
    public static HorizontalScrollView getCarousel(Context context, List<View> views){
        HorizontalScrollView root = new HorizontalScrollView(context);

        for(View v : views){
            root.addView(v);
        }
        return root;
    }
}
