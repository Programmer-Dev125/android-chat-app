package com.example.chatapp.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.window.OnBackInvokedDispatcher;

import com.example.chatapp.R;
import com.example.chatapp.domain.Modal;
import com.example.chatapp.domain.ModalCallback;
import com.example.chatapp.domain.Screen;
import com.example.chatapp.domain.Text;

public class InformationPage {
    private static Boolean isFocus = false;
    private static Boolean isModalOn = false;
    public static void toggleModal(){
        isModalOn = !isModalOn;
    }
    public static FrameLayout getInfo(Context context){
        LinearLayout layout = new LinearLayout(context);
        LinearLayout upper = new LinearLayout(context);
        EditText input = new EditText(context);

        // Orientation
        layout.setOrientation(LinearLayout.VERTICAL);

        // All Params
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                (int) (context.getResources().getDisplayMetrics().widthPixels * 0.80),
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER_HORIZONTAL
        );
        layoutParams.setMargins(0, 150, 0, 0);
        layout.setLayoutParams(layoutParams);

        // upperParams
        LinearLayout.LayoutParams upperParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        upperParams.gravity = Gravity.CENTER_HORIZONTAL;
        upper.setGravity(Gravity.CENTER_VERTICAL);
        upper.setLayoutParams(upperParams);

        // ImagePerson Params
        LinearLayout.LayoutParams imagePersonParams = new LinearLayout.LayoutParams(
                130,
                130
        );

        imagePersonParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
        imagePersonParams.setMargins(0, 0, 0, -20);

        // ImageCamera Params
        LinearLayout.LayoutParams imageCameraParams = new LinearLayout.LayoutParams(
                50,
                50
        );
        imageCameraParams.gravity = Gravity.END;

        // Input Params
        LinearLayout.LayoutParams inputParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        inputParams.setMargins(0, 40, 0, 40);
        input.setLayoutParams(inputParams);

        // Image
        ImageView imagePerson = new ImageView(context);
        ImageView imageCamera = new ImageView(context);

        imagePerson.setImageResource(R.drawable.user);
        imagePerson.setLayoutParams(imagePersonParams);
        imagePerson.setColorFilter(Color.parseColor("#a7b1b7"));

        imageCamera.setImageResource(R.drawable.camera);
        imageCamera.setLayoutParams(imageCameraParams);
        imageCamera.setColorFilter(Color.WHITE);
        imageCamera.setPadding(10, 10, 10, 10);

        // Circle
        GradientDrawable bg = new GradientDrawable();
        bg.setShape(GradientDrawable.OVAL);
        bg.setStroke(4, Color.parseColor("#a7b1b7"));
        bg.setColor(Color.TRANSPARENT);
        bg.setSize(200, 200);

        upper.setBackground(bg);

        // Camera Circle
        GradientDrawable cameraBg = new GradientDrawable();
        cameraBg.setShape(GradientDrawable.OVAL);
        cameraBg.setStroke(2, Color.WHITE);
        cameraBg.setColor(Color.parseColor("#35a897"));

        imageCamera.setBackground(cameraBg);

        upper.setOrientation(LinearLayout.VERTICAL);
        upper.addView(imagePerson);
        upper.addView(imageCamera);

        // InputBackground
        GradientDrawable inputBg = new GradientDrawable();
        inputBg.setStroke(2, Color.parseColor("#a7b1b7"));
        inputBg.setCornerRadius(20);

        input.setPadding(20, 20, 20, 20);
        input.setBackground(inputBg);

        input.setOnFocusChangeListener((inputView, hasFocus)->{
            inputBg.setStroke(2, Color.parseColor(hasFocus ? "#35a897" : "#a7b1b7"));
            isFocus = hasFocus;
        });

        layout.addView(upper);
        layout.addView(input);

        FrameLayout root = Screen.createScreen(context, layout, layoutParams, (view) ->{
            view.setOnTouchListener((scrollView, event)->{
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    if(isFocus){
                        input.clearFocus();
                        InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                        if(inputManager != null){
                            inputManager.hideSoftInputFromWindow(input.getWindowToken(), 0);
                        }
                    }
                }
                return false;
            });
        });

        root.getViewTreeObserver().addOnGlobalLayoutListener(()->{
            Rect rect = new Rect();
            root.getWindowVisibleDisplayFrame(rect);
            int screenHeight = root.getRootView().getHeight();
            int keyPadHeight = screenHeight - rect.bottom;

            if(keyPadHeight < screenHeight * 0.15){
                View current = ((Activity) context).getCurrentFocus();
                if(current != null){
                    current.clearFocus();
                }
            }
        });

        upper.setOnClickListener((view)->{
            LinearLayout layoutContent = modelContent(context);
            float[] arr =  {
                    0, 0f,
                    0f,  0f,
                    20f, 20f,
                    20f,  20f
            };
            FrameLayout modalLayout = Modal.createModal(context, layoutContent, arr, Gravity.TOP);
            ModalCallback callback = new ModalCallback() {};
            modalLayout.setOnClickListener((modalView) ->{
                callback.closeModal(root, modalLayout, layoutContent);
            });
            callback.openModal(root, modalLayout, layoutContent);
            modalLayout.dispatchApplyWindowInsets(root.getRootWindowInsets());
        });

        return root;
    }
    public static LinearLayout modelContent(Context context){
         LinearLayout layout = new LinearLayout(context);

         TextView text = Text.getTextScreeen(context, "This is the Modal text", 20);
         layout.addView(text);

         return layout;
    }
}
