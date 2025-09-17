package com.example.chatapp.data;

import android.content.Context;

import com.example.chatapp.data.repositories.CreateDb;
import com.example.chatapp.model.DatabaseState;

public final class DbConfig {
    private final CreateDb db = new CreateDb();

    public DatabaseState resultDb(Context context){
        CreateDb.DbStatus status = db.createDb(context);
        switch (status){
            case DB_CONFIG_DONE:
            case TABLES_CREATED:
                return DatabaseState.INITIALIZED;

            case USER_NOT_CREATED:
                return DatabaseState.NEEDS_USER;

            case TABLES_NOT_CREATED:
            case TABLES_CREATION_ERROR:
            case DB_CONFIG_ERROR:
            default:
                return DatabaseState.ERROR;
        }
    }
}
