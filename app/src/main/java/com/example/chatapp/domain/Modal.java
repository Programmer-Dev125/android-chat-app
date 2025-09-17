package com.example.chatapp.domain;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.chatapp.model.ScreenView;

public class Modal extends FrameLayout{

    private final ScreenView screenView = new ScreenView();

    public Modal(Context context, View content){
        super(context);
        createModal(content);
    }
    private void createModal(View content){

        // Params
        FrameLayout.LayoutParams rootParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        setLayoutParams(rootParams);

        setOnApplyWindowInsetsListener((view, windowInsets) ->{
            return screenView.onApplyInsets(windowInsets, rootParams);
        });

        // background
        setBackgroundColor(Color.argb(128, 0, 0, 0));
        addView(content);
    }
}
