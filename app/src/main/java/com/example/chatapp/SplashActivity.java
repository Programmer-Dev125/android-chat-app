package com.example.chatapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.widget.FrameLayout;

import com.example.chatapp.model.DatabaseState;
import com.example.chatapp.model.DatabaseView;
import com.example.chatapp.ui.SplashUI;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SplashActivity extends Activity {

    private final DatabaseView db = new DatabaseView(this);

    @Override
    public void onCreate(Bundle saveInstance){
        super.onCreate(saveInstance);
        FrameLayout root = SplashUI.createSplashScreen(this);
        setContentView(root);

        ExecutorService thread = Executors.newSingleThreadExecutor();

        thread.execute(() ->{
            DatabaseState state = db.hasCreated();


            runOnUiThread(() ->{
                Intent intent = new Intent(this, MainActivity.class);;
                switch (state){
                    case INITIALIZED:
                        intent.putExtra("logged", true);
                        startActivity(intent);
                        break;
                    case NEEDS_USER:
                        intent.putExtra("logged", false);
                        break;
                    case ERROR:
                        Intent restartIntent = new Intent(this, SplashActivity.class);
                        restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        finish();
                        startActivity(restartIntent);
                        return;
                }
                startActivity(intent);
            });
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){

            WindowInsetsController insetsController = getWindow().getInsetsController();
            if(insetsController != null){
                insetsController.hide(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
            }
        }else{
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
        }
    }
}
