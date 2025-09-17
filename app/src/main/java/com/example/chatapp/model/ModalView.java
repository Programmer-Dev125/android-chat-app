package com.example.chatapp.model;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public final class ModalView {

    private ViewGroup parent;
    private ViewGroup child;
    private ViewGroup content;
    public boolean isOn = false;
    public void childAdded(View child, FrameLayout.LayoutParams params, GradientDrawable bg, int[] padding){
        child.setLayoutParams(params);
        child.setBackground(bg);
        child.setPadding(padding[0], padding[1], padding[2], padding[3]);
    }

    public void openTopToBottom(Context context, ViewGroup parent, ViewGroup child, ViewGroup content){
        parent.addView(child);

        content.measure(
                View.MeasureSpec.makeMeasureSpec(
                        context.getResources().getDisplayMetrics().widthPixels,
                        View.MeasureSpec.EXACTLY
                ),
                View.MeasureSpec.makeMeasureSpec(
                        0, View.MeasureSpec.UNSPECIFIED
                )
        );
        final float height = content.getMeasuredHeight();
        content.setTranslationY(-height);
        content.animate().translationYBy(height).setDuration(200);

        this.parent = parent;
        this.child = child;
        this.content = content;
        isOn = true;
    }

    public void closeBottomToTop(){
        isOn = false;
        final float height = content.getMeasuredHeight();
        content.animate().translationY(-height).setDuration(200).withEndAction(() -> parent.removeView(child));
    }
}
