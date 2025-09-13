package com.example.chatapp.domain;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public interface ModalCallback {
    default void openModal(FrameLayout parent, FrameLayout child, LinearLayout content, Context context){
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
        content.setOnTouchListener(new View.OnTouchListener() {
            private float startY;
            private boolean dragging = false;
            private final float threshold = height * 0.50f;
            private float diff;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startY = event.getRawY();
                        dragging = true;
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        if (!dragging) break;

                        float currentY = event.getRawY();
                        diff = currentY - startY;

                        if (diff < 0) {
                            content.setTranslationY(diff);

                            if (Math.abs(diff) >= threshold) {
                                closeModal(parent, child, content);
                                dragging = false;
                            }
                        } else {
                            content.setTranslationY(0);
                        }
                        return true;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        if (!dragging) break;
                        dragging = false;

                        // snap back if not enough dragged
                        if (Math.abs(content.getTranslationY()) < threshold) {
                            content.animate().translationY(0).setDuration(200);
                        }
                        return true;
                }
                return false;
            }
        });
    }
    default void closeModal(FrameLayout parent, FrameLayout child, LinearLayout content){
        final float height = content.getMeasuredHeight();
        content.animate().translationYBy(-height).setDuration(200).withEndAction(() -> {
            parent.removeView(child);
            content.setTranslationY(-height);
        });
    }
}
