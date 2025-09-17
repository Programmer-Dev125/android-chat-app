package com.example.chatapp.model;

import android.content.Context;

import com.example.chatapp.data.DbConfig;

public class DatabaseView {
   private final DbConfig db = new DbConfig();
   public Context context;

   public DatabaseView(Context context){
       this.context = context;
   }

   public DatabaseState hasCreated(){
       return db.resultDb(context);
   }
}
