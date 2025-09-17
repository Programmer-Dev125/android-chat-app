package com.example.chatapp.model;

import android.app.Activity;
import android.content.Context;
import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Build;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

public final class ScreenView {

    public void onHideKeyboard(Context context, View view){
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        int screenHeight = view.getRootView().getHeight();
        int keyPadHeight = screenHeight - rect.bottom;

        if(keyPadHeight < screenHeight * 0.15){
            View current = ((Activity) context).getCurrentFocus();
            if(current != null){
                current.clearFocus();
            }
        }
    }
    public WindowInsets onApplyInsets(WindowInsets windowInsets, FrameLayout.LayoutParams scrollParams){
        if(!windowInsets.hasInsets()){
            return windowInsets;
        }

        int top, bottom, left, right;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            Insets insets = windowInsets.getInsets(WindowInsets.Type.systemBars());
            DisplayCutout disp = windowInsets.getDisplayCutout();

            top = insets.top;
            bottom = insets.bottom;
            left = insets.left;
            right = insets.right;

            if(disp != null){
                top = Math.max(top, disp.getSafeInsetTop());
                bottom = Math.max(bottom, disp.getSafeInsetBottom());
                left = Math.max(left, disp.getSafeInsetLeft());
                right = Math.max(right, disp.getSafeInsetRight());
            }
        }else{
            top = windowInsets.getStableInsetTop();
            bottom = windowInsets.getStableInsetBottom();
            left = windowInsets.getStableInsetLeft();
            right = windowInsets.getStableInsetRight();
        }

        scrollParams.setMargins(left, top, right, bottom);
        return windowInsets;
    }
}
