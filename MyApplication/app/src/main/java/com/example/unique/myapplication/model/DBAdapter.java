package com.example.unique.myapplication.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rezwan on 4/3/2016.
 */
public class DBAdapter {
    public static final String db_name="Workout_Pro.db";
    public static final String table_name="Login";
    public static final String col_1="Email";
    public static final String col_2="Password";

    public DBAdapter(Context context) {
    }

    public void onCreate(SQLiteDatabase db) {
    }


}
