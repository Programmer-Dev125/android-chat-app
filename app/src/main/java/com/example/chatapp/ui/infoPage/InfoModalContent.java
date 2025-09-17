package com.example.chatapp.ui.infoPage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import androidx.appcompat.widget.LinearLayoutCompat;

import com.example.chatapp.domain.RippleButton;
import com.example.chatapp.domain.Text;
import com.example.chatapp.model.ButtonView;
import com.example.chatapp.model.ModalView;

public class InfoModalContent extends LinearLayout {

    private final float[] radius =  {
            0f, 0f,
            0f, 0f,
            20f, 20f,
            20f, 20f
    };

    private final ButtonView buttonWatcher = new ButtonView();

    public InfoModalContent(Context context){
        super(context);
        modalContent(context);
    }
    public void modalContent(Context context){
          setLayoutParams(new LinearLayoutCompat.LayoutParams(
                  ViewGroup.LayoutParams.MATCH_PARENT,
                  ViewGroup.LayoutParams.WRAP_CONTENT
          ));

          Activity activity = (Activity) context;

          GradientDrawable line = new GradientDrawable();
          line.setSize(0, 30);

          GradientDrawable bg = new GradientDrawable();
          bg.setColor(Color.WHITE);
          bg.setCornerRadii(radius);

          setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
          setDividerDrawable(line);

          setOrientation(LinearLayout.VERTICAL);
          setVerticalGravity(Gravity.TOP);
          setBackground(bg);

          setPadding(20, 20, 20, 20);

          Text selText = new Text(context, "Select Image", 16, Color.WHITE);
          Text capText = new Text(context, "Capture Image", 16, Color.WHITE);

          RippleButton selBtn = new RippleButton(context, selText, Color.parseColor("#0ad35b"));
          RippleButton capBtn = new RippleButton(context, capText, Color.parseColor("#9ea4b0"));


          selBtn.setOnClickListener((selView)->{
              buttonWatcher.selImage(activity);
              ModalView.closeBottomToTop();
          });

          capBtn.setOnClickListener((capView)->{
               buttonWatcher.capImage(activity);
               ModalView.closeBottomToTop();
          });


          addView(selBtn);
          addView(capBtn);

          setOnClickListener((view)->{});
    }
}
