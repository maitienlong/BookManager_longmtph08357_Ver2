package com.example.bookmanager_longmtph08357.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bookmanager_longmtph08357.database.BookReadSQLite;
import com.example.bookmanager_longmtph08357.model.Book;
import com.example.bookmanager_longmtph08357.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

import static com.example.bookmanager_longmtph08357.database.BookReadSQLite.C_GIATHANH;
import static com.example.bookmanager_longmtph08357.database.BookReadSQLite.C_MASACH;
import static com.example.bookmanager_longmtph08357.database.BookReadSQLite.C_NXB;
import static com.example.bookmanager_longmtph08357.database.BookReadSQLite.C_SOLUONG;
import static com.example.bookmanager_longmtph08357.database.BookReadSQLite.C_TACGIA;
import static com.example.bookmanager_longmtph08357.database.BookReadSQLite.C_TENSACH;
import static com.example.bookmanager_longmtph08357.database.BookReadSQLite.T_BOOK;

public class SachDAO {

    private BookReadSQLite bookReadSQLite;

    public SachDAO(Context context) {
        bookReadSQLite = new BookReadSQLite(context);
    }

    //------------------------------------------------ THÊM SÁCH  -------------------------------------------------------

    public long insertBook(Book book) {
        SQLiteDatabase sqLiteDatabase = bookReadSQLite.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(C_MASACH, book.maSach);
        contentValues.put(C_TENSACH, book.tenSach);
        contentValues.put(C_TACGIA, book.tacGia);
        contentValues.put(C_NXB, book.nhaXuatBan);
        contentValues.put(C_GIATHANH, book.giaThanh);
        contentValues.put(C_SOLUONG, book.soLuong);

        long result = sqLiteDatabase.insert(T_BOOK, null, contentValues);
        Log.e("B",result+"");

        sqLiteDatabase.close();

        return result;
    }


    //------------------------------------------------ LẤY DS THỂ LOẠI  -------------------------------------------------------

    public List<Book> getAllBook() {

        List<Book> bookList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = bookReadSQLite.getReadableDatabase();

        String SELECT = "SELECT * FROM " + T_BOOK;

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.maSach = Integer.parseInt(cursor.getString(0));
                book.theLoaiSach = cursor.getString(1);
                book.tenSach = cursor.getString(2);
                book.tacGia = cursor.getString(3);
                book.nhaXuatBan = cursor.getString(4);
                book.giaThanh = Integer.parseInt(cursor.getString(5));
                book.soLuong = Integer.parseInt(cursor.getString(6));
                bookList.add(book);
            } while (cursor.moveToNext());
        }

        sqLiteDatabase.close();

        return bookList;
    }



}
