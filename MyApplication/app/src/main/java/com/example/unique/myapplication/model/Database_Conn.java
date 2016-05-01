package com.example.unique.myapplication.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rezwan on 4/3/2016.
 */
public class Database_Conn extends SQLiteOpenHelper {
    public static final String db_name="Workout_Pro.db";
    public static final String table_name="Login";
    public static final String col_1="Email";
    public static final String col_2="Password";
    
    public Database_Conn(Context context) {
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+table_name+" (Email Text,Password Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS "+table_name);
        onCreate(db);

    }

    public boolean Insert(String email,String password)
    {
        ContentValues cv=new ContentValues();
        cv.put(col_1,email);
        cv.put(col_2,password);
        SQLiteDatabase db=this.getWritableDatabase();
        long result=db.insert(table_name,null,cv);

        if(result==-1)
        {
            return false;
        }
        else
            return true;
    }

    public Cursor retrieveData()
    {
        SQLiteDatabase sdata=this.getReadableDatabase();
        Cursor cr=sdata.rawQuery("select * from "+table_name,null);
        return cr;
    }
}
