package com.example.chatapp.domain;

import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.example.chatapp.model.ScreenView;

public abstract class Screen extends FrameLayout{

    private final ScreenView screenView = new ScreenView();

    public Screen(Context context){
        super(context);
        createScreen(context, createContent(context));
    }

    protected abstract FrameLayout createContent(Context context);

    private void createScreen(Context context, View layout){
        ScrollView scrollView = new ScrollView(context);

        FrameLayout.LayoutParams scrollParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        setOnApplyWindowInsetsListener((rootView, windowInsets) ->{
           return screenView.onApplyInsets(windowInsets, scrollParams);
        });

        scrollView.setBackgroundColor(Color.WHITE);
        scrollView.setFillViewport(true);
        scrollView.addView(layout);

        getViewTreeObserver().addOnGlobalLayoutListener(() ->{
            screenView.onHideKeyboard(context, this);
        });

        addView(scrollView, scrollParams);
    }
}
