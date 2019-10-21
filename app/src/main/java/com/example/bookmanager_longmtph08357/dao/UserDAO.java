package com.example.bookmanager_longmtph08357.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookmanager_longmtph08357.database.BookReadSQLite;
import com.example.bookmanager_longmtph08357.model.User;


import java.util.ArrayList;
import java.util.List;

import static com.example.bookmanager_longmtph08357.database.BookReadSQLite.C_FULLNAME;
import static com.example.bookmanager_longmtph08357.database.BookReadSQLite.C_PASSWORD;
import static com.example.bookmanager_longmtph08357.database.BookReadSQLite.C_PHONE;
import static com.example.bookmanager_longmtph08357.database.BookReadSQLite.C_USERNAME;
import static com.example.bookmanager_longmtph08357.database.BookReadSQLite.T_NAME;


public class UserDAO {
    private BookReadSQLite bookReadSQLite;

    public UserDAO(Context context) {
        bookReadSQLite = new BookReadSQLite(context);
    }

    public long insertCar(User user) {
        SQLiteDatabase sqLiteDatabase = bookReadSQLite.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(C_USERNAME, user.username);
        contentValues.put(C_PASSWORD, user.password);
        contentValues.put(C_PHONE, user.phone);
        contentValues.put(C_FULLNAME,user.fullname);


        long result = sqLiteDatabase.insert(T_NAME, null, contentValues);


        sqLiteDatabase.close();

        return result;
    }


    //---------------------------- GET ALL --------------------------------------

    public List<User> getAll() {
        List<User> userList = new ArrayList<>();


        //b1:xin quyen
        SQLiteDatabase sqLiteDatabase = bookReadSQLite.getReadableDatabase();

        //b2:select
        String SELECT = "SELECT * FROM " + T_NAME;

        //b3:lam viec voi Cursor
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.username = Integer.parseInt(cursor.getString(0));
                user.password = cursor.getString(1);
                user.phone = cursor.getString(2);
                user.fullname = cursor.getString(3);

                userList.add(user);
            } while (cursor.moveToNext());
        }

        //b4:dong sql
        sqLiteDatabase.close();

        return userList;
    }

    //---------------------------- DELETE --------------------------------------

    public int deleteUser(int id) {
        //b1:xin quyen
        SQLiteDatabase sqLiteDatabase = bookReadSQLite.getWritableDatabase();

        //b2:delete
        int result = sqLiteDatabase.delete(T_NAME, C_USERNAME + "=?", new String[]{String.valueOf(id)});

        //b3:dong sql
        sqLiteDatabase.close();

        return result;
    }

}
