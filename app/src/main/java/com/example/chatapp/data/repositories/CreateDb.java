package com.example.chatapp.data.repositories;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class CreateDb {

    private static final String TAG = "CreateDb";

    // Database name
    public static final String DB_NAME = "chatDb.db";

    // Table names
    public static final String TABLE_USERS = "users";
    public static final String TABLE_GROUPS = "chat_groups";
    public static final String TABLE_CHAT = "chat";
    public static final String TABLE_GROUP_MEMBERS = "group_members";
    public static final String TABLE_STATUS = "status";

    // Required tables
    private static final Set<String> REQUIRED_TABLES = new HashSet<String>() {{
        add(TABLE_USERS);
        add(TABLE_GROUPS);
        add(TABLE_CHAT);
        add(TABLE_GROUP_MEMBERS);
        add(TABLE_STATUS);
    }};

    // Database status enum
    public enum DbStatus {
        TABLES_NOT_CREATED,
        TABLES_CREATED,
        TABLES_CREATION_ERROR,
        DB_CONFIG_DONE,
        USER_NOT_CREATED,
        DB_CONFIG_ERROR
    }

    public DbStatus createDb(Context context) {
        File dbFile = context.getDatabasePath(DB_NAME);

        if (dbFile.exists()) {
            try (SQLiteDatabase readDb = SQLiteDatabase.openDatabase(
                    dbFile.getPath(),
                    null,
                    SQLiteDatabase.OPEN_READONLY
            )) {
                if (!checkIfTablesExist(readDb, REQUIRED_TABLES)) {
                    return DbStatus.TABLES_NOT_CREATED;
                }

                int userCount = getUserCount(readDb);
                if (userCount >= 2) {
                    return (userCount == 2) ? DbStatus.USER_NOT_CREATED : DbStatus.DB_CONFIG_DONE;
                } else {
                    return DbStatus.DB_CONFIG_ERROR;
                }
            } catch (Exception e) {
                return DbStatus.DB_CONFIG_ERROR;
            }
        }

        DatabaseErrorHandler handler = dbObj -> {
            try {
                dbObj.close();
            } catch (Exception ignored) {
            }
            new File(dbObj.getPath()).delete();
        };

        try (SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbFile.getPath(), null, handler)) {
            createTables(db);
            insertDefaultAgents(db);
            return DbStatus.TABLES_CREATED;
        } catch (Exception e) {
            return DbStatus.TABLES_CREATION_ERROR;
        }
    }

    private boolean checkIfTablesExist(SQLiteDatabase db, Set<String> tableNames) {
        if (db == null || !db.isOpen()) return false;

        Set<String> foundTables = new HashSet<>();
        StringBuilder placeholders = new StringBuilder();
        String[] args = new String[tableNames.size()];
        int i = 0;

        for (String table : tableNames) {
            if (i > 0) placeholders.append(",");
            placeholders.append("?");
            args[i++] = table;
        }

        try (Cursor cursor = db.rawQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name IN (" + placeholders + ")",
                args
        )) {
            while (cursor.moveToNext()) {
                foundTables.add(cursor.getString(0));
            }
        }

        return foundTables.containsAll(tableNames);
    }

    private int getUserCount(SQLiteDatabase db) {
        try (Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_USERS, null)) {
            if (cursor.moveToFirst()) {
                return cursor.getInt(0);
            }
        }
        return 0;
    }

    private void insertDefaultAgents(SQLiteDatabase db) {
        db.execSQL("INSERT INTO " + TABLE_USERS + " (name) VALUES ('Agent One')");
        db.execSQL("INSERT INTO " + TABLE_USERS + " (name) VALUES ('Agent Two')");
    }

    private void createTables(SQLiteDatabase db) {
        // Users
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL," +
                "image_path TEXT)"
        );

        // Groups
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_GROUPS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "image_path TEXT," +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP)"
        );

        // Chat
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_CHAT + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "chat_type TEXT NOT NULL," +
                "sender_id INTEGER NOT NULL," +
                "receiver_id INTEGER," +
                "group_id INTEGER," +
                "message_type TEXT NOT NULL," +
                "content TEXT NOT NULL," +
                "send_at DATETIME DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY(sender_id) REFERENCES " + TABLE_USERS + "(id)," +
                "FOREIGN KEY(receiver_id) REFERENCES " + TABLE_USERS + "(id)," +
                "FOREIGN KEY(group_id) REFERENCES " + TABLE_GROUPS + "(id)" +
                ")");

        // Group members
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_GROUP_MEMBERS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "group_id INTEGER NOT NULL," +
                "user_id INTEGER NOT NULL," +
                "joined_at DATETIME DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY(group_id) REFERENCES " + TABLE_GROUPS + "(id)," +
                "FOREIGN KEY(user_id) REFERENCES " + TABLE_USERS + "(id)" +
                ")");

        // Status
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_STATUS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER NOT NULL," +
                "content_type TEXT NOT NULL," +
                "image_path TEXT," +
                "content_message TEXT," +
                "bg_color TEXT," +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY(user_id) REFERENCES " + TABLE_USERS + "(id))");
    }
}
