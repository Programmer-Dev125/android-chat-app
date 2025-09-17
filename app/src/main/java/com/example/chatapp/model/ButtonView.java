package com.example.chatapp.model;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;

public final class ButtonView {
    public void onClick(){}
    public void selImage(Activity activity){
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        intent.setType("image/*");
        activity.startActivityForResult(intent, 2);
    }
    public void capImage(Activity activity){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(intent, 1);
    }
}
