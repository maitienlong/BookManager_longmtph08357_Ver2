package com.example.bookmanager_longmtph08357.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookReadSQLite extends SQLiteOpenHelper {
    public BookReadSQLite(Context context) {
        super(context, "SQLiteVer1.db", null, 1);
    }

    public final static String T_NAME = "userTable";
    public final static String C_USERNAME = "username";
    public final static String C_PASSWORD = "password";
    public final static String C_PHONE = "phone";
    public final static String C_FULLNAME = "fullname";

    public final static String CREATE_TABLE = "CREATE TABLE userTable (username INTEGER PRIMARY KEY,password NVARCHAR,phone VARCHAR,fullname NVARCHAR)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
