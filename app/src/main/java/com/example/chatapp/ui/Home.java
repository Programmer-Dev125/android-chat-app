package com.example.chatapp.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.helper.widget.Grid;

import com.example.chatapp.R;
import com.example.chatapp.domain.Text;

public class Home {
    public static FrameLayout getHomeScreen(Context context){
        FrameLayout root = new FrameLayout(context);
        root.addView(getUpperView(context));
        return root;
    }
    private static LinearLayout getUpperView(Context context){
        LinearLayout rootLayout = new LinearLayout(context);
        GridLayout grids = new GridLayout(context);

        grids.setOrientation(GridLayout.HORIZONTAL);
        grids.setRowCount(1);
        grids.setColumnCount(2);

        LinearLayout.LayoutParams gridParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        TextView title = Text.getTextScreeen(context, "ChatApp", 20);
        ImageView image = new ImageView(context);

        image.setImageResource(R.drawable.setting);
//        image.setColorFilter(Color.parseColor("#0a6057"));

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.parseColor("#e2e7ea"));
        bg.setShape(GradientDrawable.RING);
        bg.setSize(40, 40);

        image.setBackground(bg);

        ViewGroup.LayoutParams viewParams = new ViewGroup.LayoutParams(
          20,
          20
        );

        image.setLayoutParams(viewParams);

        GridLayout.LayoutParams titleParams = new GridLayout.LayoutParams();
        GridLayout.LayoutParams iconParams = new GridLayout.LayoutParams();

        titleParams.columnSpec = GridLayout.spec(0, 1, 1f);
        iconParams.columnSpec = GridLayout.spec(1, 1, 1f);

        titleParams.setGravity(Gravity.LEFT);
        iconParams.setGravity(Gravity.LEFT);

        grids.addView(title, titleParams);
        grids.addView(image, iconParams);

        rootLayout.addView(grids, gridParams);
        return rootLayout;
    }
    private static LinearLayout getBottomView(Context context){
        LinearLayout rootLayout = new LinearLayout(context);
        return rootLayout;
    }
}
