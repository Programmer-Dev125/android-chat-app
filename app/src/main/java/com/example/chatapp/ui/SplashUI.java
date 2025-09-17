package com.example.chatapp.ui;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;


import com.example.chatapp.R;
import com.example.chatapp.domain.Loader;


public class SplashUI {
    public static FrameLayout createSplashScreen(Context context){
        FrameLayout root = new FrameLayout(context);
        ImageView image = new ImageView(context);
        View loader = new View(context);

        FrameLayout.LayoutParams rootParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        root.setLayoutParams(rootParams);

        FrameLayout.LayoutParams imageParams = new FrameLayout.LayoutParams(
                (int) (context.getResources().getDisplayMetrics().widthPixels * 0.50),
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL
        );

        FrameLayout.LayoutParams loaderParams = new FrameLayout.LayoutParams(
                50,
                50,
                Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM
        );

        loader.setLayoutParams(loaderParams);
        loaderParams.setMargins(0, 0, 0, 100);

        Drawable bg = new Loader(Color.BLACK, 10);

        loader.setBackground(bg);

        image.setLayoutParams(imageParams);
        image.setImageResource(R.drawable.icon);

        image.setScaleX(0.5f);
        image.setScaleY(0.5f);

        image.post(() -> {
            image.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(1000)
                    .start();
        });

        root.addView(image);
        root.addView(loader);


        return root;
    }
}
