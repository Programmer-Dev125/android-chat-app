package com.example.chatapp.domain;

import android.content.Context;
import android.widget.EditText;

public class Input {
    public static EditText createInput(Context context){
        EditText input = new EditText(context);
        return input;
    }
}
