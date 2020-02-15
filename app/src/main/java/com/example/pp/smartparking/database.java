package com.example.pp.smartparking;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {
    public database(Context context) {
        super(context, "userauth", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create TABLE USERAUT(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,USERNAME TEXT ,PASSWORD INTEGER) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
