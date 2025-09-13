package com.example.chatapp.domain;

import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public interface ModalCallback {
    default void openModal(FrameLayout parent, FrameLayout child, LinearLayout content){
        parent.addView(child);

        content.post(() ->{
            System.out.println(content.getTranslationY());
        });
    }
    default void closeModal(FrameLayout parent, FrameLayout child, LinearLayout content){
        float height = content.getHeight();
        content.animate().translationYBy(-height).withEndAction(() -> {
            parent.removeView(child);
        }).start();
    }
}
