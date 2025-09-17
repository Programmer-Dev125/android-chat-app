package com.example.chatapp.model;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.provider.MediaStore;
import android.view.View;

public final class ButtonView {

    public boolean loading = false;
    public void selImage(Activity activity){
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        intent.setType("image/*");
        activity.startActivityForResult(intent, 2);
    }
    public void capImage(Activity activity){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(intent, 1);
    }
    public void onNext(View btnView){
        if(loading){
            btnView.setClickable(false);
        }
        GradientDrawable bg = (GradientDrawable) btnView.getBackground();
        System.out.println(bg);
    }
}
