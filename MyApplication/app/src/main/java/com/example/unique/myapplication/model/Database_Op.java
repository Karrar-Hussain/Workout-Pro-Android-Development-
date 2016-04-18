package com.example.unique.myapplication.model;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.security.AccessController.getContext;

/**
 * Created by Unique on 4/16/2016.
 */
public class Database_Op extends SQLiteOpenHelper {
    public static final String db_name="Workout_Pro.db";
    public static final String table_name="Exercise_tbl";

    public static final String name="Name";
    public static final String muscle_type="Muscle_Type";
    public static final String secondary_type="Secondary_Type";
    public static final String tip="Tip";
    public static final String motion="Motion";
    public static final String precaution="Precaution";
    public static final String gif="Gif";

    public SQLiteDatabase dbs;
    private Context ctx;
    public Database_Op(Context context) {
        super(context, db_name, null, 1);
        ctx=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + table_name + " (Name Text,Gif Text PRIMARY KEY,Muscle_Type Text,Secondary_Type Text,Tip Text,Motion Text,Precaution Text)");
        dbs=db;
        //Insert();
        //db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }
    public void Delete()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_name, null, null);
        db.close();
    }
    public boolean Update(List gifList)
    {
        String Name="Exercise Name",Secondary_Type="Secondary Muscle Target: ",Muscel_Type="Main Muscle Target",Tip="Start Point:See animation How to Start!", Motion="Motion: See animation motion!", Precaution="Caution: Follow The anime Exactly";
        int Gif=0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        boolean bol=true;
        InputStream antidots = null;
         //<-- call getAssets on your Context object.
        try {
            antidots = ctx.getAssets().open("Data.txt");
            InputStreamReader input = new InputStreamReader(antidots);
            BufferedReader reader = new BufferedReader(input);
            String line = "";
                while ((line = reader.readLine()) != null) {
                    
                    Name = line;
                    reader.readLine();//targeted
                    Muscel_Type = reader.readLine();
                    Secondary_Type=reader.readLine();//secondary
                    reader.readLine();//instruction
                    Tip = reader.readLine();//start point
                    Motion = reader.readLine();//motion
                    Precaution = reader.readLine();//Tip/Precaution
                    line = reader.readLine();
                    Log.d("Hell in Gif parse===>>>: ", line);
                    
                   Gif= Integer.parseInt(String.valueOf(line));
                if(Gif<100&&Gif>200&&Gif<300&&Gif>400)
                {
                    Name="Exercise Name";Secondary_Type="Secondary Muscle Target: ";Muscel_Type="Main Muscle Target";Tip="Start Point:See animation How to Start!"; Motion="Motion: See animation motion!"; Precaution="Caution: Follow The anime Exactly";
                }
                    cv.put(name, Name);
                //cv.put(gif + "", Gif + "");
                cv.put(muscle_type, Muscel_Type);
                cv.put(secondary_type,Secondary_Type);
                cv.put(tip, Tip);
                cv.put(motion, Motion);
                cv.put(precaution, Precaution);
                //SQLiteDatabase db = this.getWritableDatabase();
                long result=0;
                try{
                    result = db.update(table_name, cv, " Gif = " + Gif, null);
                }catch (Exception e)
                {
                    result=0;
                }
                if (result == -1) {
                    Log.d("Fish ===>>Message: Hell data error", "Ooolallala");
                    //bol = false;
                } else {
                    Log.d("Fish ===>>Message: ", "Correctly entered");
                    //bol = true;
                    cv.clear();
                }

            }} catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=0;i<gifList.size();i++) {

            if(Gif<100&&Gif>200&&Gif<300&&Gif>400)
            {
                Name="Exercise Name";Secondary_Type="Secondary Muscle Target: ";Muscel_Type="Main Muscle Target";Tip="Start Point:See animation How to Start!"; Motion="Motion: See animation motion!"; Precaution="Caution: Follow The anime Exactly";
            }
            cv.put(name, Name);
            //cv.put(gif + "", Gif + "");
            cv.put(muscle_type, Muscel_Type);
            cv.put(secondary_type, Secondary_Type);
            cv.put(tip, Tip);
            cv.put(motion, Motion);
            cv.put(precaution, Precaution);
            //SQLiteDatabase db = this.getWritableDatabase();
            long result = 0;
            try {
                result = db.update(table_name, cv, " Gif = " + Gif, null);
            } catch (Exception e) {
                result = 0;
            }
            if (result == -1) {
                Log.d("Fish ===>>Message: Hell data error", "Ooolallala");
                //bol = false;
            } else {
                Log.d("Fish ===>>Message: ", "Correctly entered");
                //bol = true;
                cv.clear();
            }
        }
        return bol;

    }

    public boolean InsertDummy(List gifList)
    {
        String Name="Exercise Name",Secondary_Type="Secondary Muscle Target: ",Muscel_Type="Main Muscle Target",Tip="Start Point:See animation How to Start!", Motion="Motion: See animation motion!", Precaution="Caution: Follow The anime Exactly";
        int Gif;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        boolean bol=true;
        String str;
            for (int i=0;i<gifList.size();i++) {
                str = gifList.get(i).toString();
                //str.replace();
                str = str.replace(".gif", "");
                Gif = Integer.parseInt(String.valueOf(str));
                if (Gif < 100)
                    Muscel_Type = "Cardio";
                if (Gif >= 100 && Gif < 200)
                    Muscel_Type = "Abs";
                else if (Gif >= 200 && Gif < 300)
                    Muscel_Type = "Back";
                else if (Gif >= 300 && Gif < 400)
                    Muscel_Type = "Chest";
                else if (Gif >= 400 && Gif < 500)
                    Muscel_Type = "Shoulders";
                else if (Gif >= 500 && Gif < 600)
                    Muscel_Type = "Legs";
                else if (Gif >= 600 && Gif < 700)
                    Muscel_Type = "Calves";
                else if (Gif >= 700 && Gif < 800)
                    Muscel_Type = "Biceps";
                else if (Gif >= 800 && Gif < 900)
                    Muscel_Type = "Triceps";
                else if (Gif >= 900 && Gif < 1000)
                    Muscel_Type = "Forearms";
                Name = Muscel_Type + "-" + Gif;
                cv.put(name, Name);
                cv.put(gif + "", Gif+"");
                cv.put(muscle_type, Muscel_Type);
                cv.put(secondary_type, Secondary_Type);
                cv.put(tip, Tip);
                cv.put(motion, Motion);
                cv.put(precaution, Precaution);
                //SQLiteDatabase db = this.getWritableDatabase();
                long result = 0;
                try {
                    result = db.insert(table_name, null, cv);
                } catch (Exception e) {
                    result = 0;
                }
                if (result == -1) {
                    Log.d("Fish ===>>Message: Hell data error", "Ooolallala");
                    //bol = false;
                } else {
                    Log.d("Fish ===>>Message: ", "Correctly entered");
                    //bol = true;
                    cv.clear();
                }
            }

        return bol;
    }
    public boolean Insert()  {

        String Name="Exercise Name",Secondary_Type="Secondary Muscle Target: ",Muscel_Type="Main Muscle Target",Tip="Start Point:See animation How to Start!", Motion="Motion: See animation motion!", Precaution="Caution: Follow The anime Exactly";
        int Gif;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        boolean bol=true;
        InputStream antidots = null; //<-- call getAssets on your Context object.
        try {
            antidots = ctx.getAssets().open("Data.txt");
        InputStreamReader input = new InputStreamReader(antidots);
            BufferedReader reader = new BufferedReader(input);
            String line = "";
                while ((line = reader.readLine()) != null) {
                    Name = line;
                    reader.readLine();//targeted
                    Muscel_Type = reader.readLine();
                    Secondary_Type=reader.readLine();//secondary
                    reader.readLine();//instruction
                    Tip = reader.readLine();//start point
                    Motion = reader.readLine();//motion
                    Precaution = reader.readLine();//Tip/Precaution
                    line = reader.readLine();
                    Log.d("Hell in Gif parse===>>>: ", line);

                    cv.put(name, Name);
                    cv.put(gif + "", line );
                    cv.put(muscle_type, Muscel_Type);
                    cv.put(secondary_type,Secondary_Type);
                    cv.put(tip, Tip);
                    cv.put(motion, Motion);
                    cv.put(precaution, Precaution);
                //SQLiteDatabase db = this.getWritableDatabase();
                    long result=0;
                    try{
                        result = db.insert(table_name, null, cv);
                    }catch (Exception e)
                    {
                        result=0;
                    }
                    if (result == -1) {
                        Log.d("Fish ===>>Message: Hell data error", "Ooolallala");
                        //bol = false;
                    } else {
                        Log.d("Fish ===>>Message: ", "Correctly entered");
                        //bol = true;
                        cv.clear();
                    }

            }} catch (IOException e) {
            e.printStackTrace();
        }

        return bol;
    }

    public Cursor retrieveData(int range)
    {
        SQLiteDatabase sdata=this.getReadableDatabase();
        int upper=range+99;
        Cursor cr=sdata.rawQuery("select * from "+table_name+" where Gif BETWEEN "+range+" AND "+upper+"",null);
        return cr;
    }
}
