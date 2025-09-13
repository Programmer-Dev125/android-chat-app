package com.example.chatapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.WindowInsetsController;
import android.widget.FrameLayout;

import com.example.chatapp.domain.Screen;
import com.example.chatapp.model.NavigationModel;
import com.example.chatapp.ui.InformationPage;


public class MainActivity extends Activity{

    private final NavigationModel navModel = new NavigationModel();

    @Override
    public void onCreate(Bundle saveInstance){
        super.onCreate(saveInstance);
        String currScreen = navModel.getCurrentScreen();
        FrameLayout root = InformationPage.getInfo(this);
        setContentView(root);
    }

    @Override
    public void onResume(){
        super.onResume();
        View rootView = getWindow().getDecorView();
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.BLACK);
        rootView.setBackground(bg);
        getWindow().setNavigationBarColor(Color.BLACK);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            WindowInsetsController controller = getWindow().getInsetsController();
            if(controller != null){
                controller.setSystemBarsAppearance(
                        WindowInsetsController.APPEARANCE_TRANSPARENT_CAPTION_BAR_BACKGROUND,
                        WindowInsetsController.APPEARANCE_TRANSPARENT_CAPTION_BAR_BACKGROUND
                );
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
           System.out.println("This is capture");
           System.out.println(data);
        }else if(requestCode == 2 && resultCode == RESULT_OK && data != null){
           System.out.println("this is selected");
           System.out.println(data);
        }
    }
}