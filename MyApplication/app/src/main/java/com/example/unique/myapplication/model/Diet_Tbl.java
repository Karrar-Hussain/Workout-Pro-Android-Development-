package com.example.unique.myapplication.model;

/**
 * Created by Unique on 4/27/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.unique.myapplication.control.Diet_Scheduler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.StreamHandler;

public class Diet_Tbl extends SQLiteOpenHelper{
    public static final String db_name="Workout_Pro.db";
    public static final String table_name="Diet_Table";

    public static final String food_name="Food_Name";
    public static final String food_type="Food_Type";
    public static final String unit="Unit";
    public static final String calories="Calories";

    private static Context ctx;
    public Diet_Tbl(Context context) {
        super(context, db_name, null, 1);
        ctx=context;
    }
    public SQLiteDatabase dbs;


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + table_name + " (Food_Name Text , Food_Type Text , Unit Text, Calories Text)");
        dbs=db;
    }
    ArrayList<String> words = new ArrayList<>();
    public void FetchFile() {
        try {
            InputStream antidots = null;
            //<-- call getAssets on your Context object.
            antidots = ctx.getAssets().open("diet_data.txt");
            InputStreamReader input = new InputStreamReader(antidots);
            BufferedReader reader = new BufferedReader(input);
            String lineJustFetched = null;
            String[] wordsArray;

            while (true) {
                lineJustFetched = reader.readLine();
                if (lineJustFetched == null) {
                    break;
                } else {
                    wordsArray = lineJustFetched.split("\t");
                    for (String each : wordsArray) {
                        if (!"".equals(each)) {
                            words.add(each);
                            Log.d("HHHHHHHHHHHHHHHHHH>>>--------------=====>>>>> ..: ",each);
                        }
                    }
                }
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Delete()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_name, null, null);
        db.close();
    }
    public void InputInDB()
    {
        ContentValues cv=new ContentValues();
        String fruit="Fruits",vegs="Vegs",meet="Meets",fastfood="FastFoods",drink="Drinks",foodtype="foodtype";
        SQLiteDatabase db = this.getWritableDatabase();
        for(int i=0;i<words.size();i+=3)
        {
            cv.put(food_name,words.get(i));
            if(i<11*3)
                foodtype=fruit;
            else if(i<19*3)
                foodtype=vegs;
            else if(i<25*3)
                foodtype=meet;
            else if(i<36*3)
                foodtype=fastfood;
            else if(i<43*3)
                foodtype=drink;
            cv.put(food_type, foodtype);
            cv.put(unit,words.get(i+1));
            cv.put(calories, words.get(i+2));
            long result = 0;
            try {
                result = db.insert(table_name, null, cv);
            } catch (Exception e) {
                result = 0;
            }
            if (result == -1) {
                Log.d("Fish >>>------------------===>>Message: Hell data error", "Diet Database tAble");
                //bol = false;
            } else {
                Log.d("Fish >>------------------===>>>>>Message: ", "Correctly entered");
                //bol = true;
                cv.clear();
            }
        }
        db.close();
    }
    public Cursor retrieveAllFoods()
    {
        SQLiteDatabase sdata=this.getReadableDatabase();
        Cursor cr=sdata.rawQuery("select * from "+table_name ,null);
        return cr;
    }

    public Cursor retrieveFood(String food) {

        SQLiteDatabase sdata=this.getReadableDatabase();
        return sdata.rawQuery("select * from "+table_name+ " where Food_Name = "+food,null);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}