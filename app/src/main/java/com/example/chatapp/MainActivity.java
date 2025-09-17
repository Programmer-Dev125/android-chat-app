package com.example.chatapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ImageDecoder;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.WindowInsetsController;
import android.widget.FrameLayout;

import com.example.chatapp.model.NavigationView;
import com.example.chatapp.ui.infoPage.InformationPage;
import com.example.chatapp.ui.Landing;


public class MainActivity extends Activity {

    private final NavigationView navModel = new NavigationView();
    private InformationPage infoPage;

    @Override
    public void onCreate(Bundle saveInstance){
        super.onCreate(saveInstance);
        boolean logged = getIntent().getBooleanExtra("logged", false);
        infoPage = new InformationPage(this);
        FrameLayout root = logged ? new Landing(this) : infoPage;
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
           Bundle extras = data.getExtras();
           Bitmap mapImage = (Bitmap) extras.get("data");
           if(mapImage != null){
               infoPage.setImagePerson(mapImage);
           }
        }else if(requestCode == 2 && resultCode == RESULT_OK && data != null){
           try{
               Uri selImage = data.getData();
               ImageDecoder.Source source = ImageDecoder.createSource(getContentResolver(), selImage);
               Bitmap map = ImageDecoder.decodeBitmap(source);
               if(map != null){
                   infoPage.setImagePerson(map);
               }
           }catch (Exception err){
               System.out.println(err.getMessage());
           }
        }
    }
    @Override
    public void onBackPressed(){
        System.out.println("nothing");
    }
}