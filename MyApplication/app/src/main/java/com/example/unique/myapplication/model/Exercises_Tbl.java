package com.example.unique.myapplication.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.example.unique.myapplication.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Unique on 4/16/2016.
 */
public class Exercises_Tbl extends SQLiteOpenHelper {
    public static final String db_name="Workout_Pro.db";
    public static final String table_name="Exercise_tbl";

    public static final String name="Name";
    public static final String muscle_type="Muscle_Type";
    public static final String secondary_type="Secondary_Type";
    public static final String tip="Tip";
    public static final String motion="Motion";
    public static final String precaution="Precaution";
    public static final String gif="Gif";

    public static final String foodTable_name="Diet_Table";

    public static final String food_name="Food_Name";
    public static final String food_type="Food_Type";
    public static final String unit="Unit";
    public static final String calories="Calories";


    public static final String music_tbl="Music_Tbl";
    public static final String music_title="Music_Title";
    public static final String music_path="Music_Path";

    public static final String login_tbl="Login";
    public static final String col_1="Name";
    public static final String col_2="Password";

    public SQLiteDatabase dbs;
    private Context ctx;
    public Exercises_Tbl(Context context) {
        super(context, db_name, null, 1);
        ctx=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + table_name + " (Name Text,Gif Text PRIMARY KEY,Muscle_Type Text,Secondary_Type Text,Tip Text,Motion Text,Precaution Text)");
        db.execSQL("create table " + foodTable_name + " (Food_Name Text , Food_Type Text , Unit Text, Calories Text)");
        db.execSQL("create table " + music_tbl + " (Music_Title Text , Music_Path Text )");

        db.execSQL("create table Login (Name Text,Password Text)");
        db.execSQL("create table Person_Table (User_Id INTEGER PRIMARY KEY AUTOINCREMENT,Name varchar,DOB date,Age int,Occ_Type int,Main_Goal int,Height float,Weight float,Pay int)");
        dbs=db;
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly())
        {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        db.execSQL("DROP TABLE IF EXISTS " + foodTable_name);
        db.execSQL("DROP TABLE IF EXISTS " + music_tbl);
        db.execSQL("DROP TABLE IF EXISTS " + login_tbl);
        onCreate(db);
        //db.close();
    }
    public void CreateTables()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("create table "+login_tbl+" (Name Text,Password Text)");
    }

    public boolean Insert(String name,String password)
    {
        ContentValues cv=new ContentValues();
        cv.put(col_1,name);
        cv.put(col_2,password);
        SQLiteDatabase db=this.getWritableDatabase();
        long result=db.insert(login_tbl,null,cv);

        if(result==-1)
        {
            return false;
        }
        else
            return true;
    }

    public boolean InsertIntoPerson(String name,int age,int occ_type,int goal_type,float height,float weight,int pay)
    {
        ContentValues cv=new ContentValues();
        cv.put("Name",name);
        cv.put("Age",age);
        cv.put("DOB","23-2-1990");
        cv.put("Occ_Type",occ_type);
        cv.put("Main_Goal",goal_type);
        cv.put("Height",height);
        cv.put("Weight",weight);
        cv.put("Pay",pay);
        SQLiteDatabase db=this.getWritableDatabase();
        long result=db.insert("Person_Table",null,cv);

        if(result==-1)
        {
            return false;
        }
        else
            return true;
    }

    public Cursor UpdatePerson(String name,int age,int occ_type,int goal_type,float height,float weight,int pay)
    {
        ContentValues cv=new ContentValues();
        //cv.put("Name",name);
        //cv.put("Age",age);
        //cv.put("DOB","23-2-1990");
        //cv.put("Occ_Type",occ_type);
        //cv.put("Main_Goal",goal_type);
        //cv.put("Height",height);
        //cv.put("Weight",weight);
        //cv.put("Pay",pay);
        SQLiteDatabase db=this.getWritableDatabase();
        //long result=db.update("Person_Table",cv,"Name="+name,null);

        Cursor reslt1=db.rawQuery("UPDATE Person_Table SET Age="+age+",Occ_Type="+occ_type+",Main_Goal="+goal_type+",Height="+height+",Weight="+weight+",Pay="+pay+" WHERE Name=\""+name+"\"",null);
        //if(reslt1==-1)
        {
            //    return false;
        }
        //else
        //  return true;
        return reslt1;
    }

    public Cursor ChangePassword(String curr_pass,String new_pass)
    {
        SQLiteDatabase sdata=this.getWritableDatabase();
        Cursor cr=sdata.rawQuery("UPDATE "+login_tbl+" SET Password="+new_pass+" WHERE Password=\""+curr_pass+"\"",null);
        return cr;

    }


    public Cursor loginData(String username,String password)
    {
        SQLiteDatabase sdata=this.getWritableDatabase();
        Cursor cr=sdata.rawQuery("select * from "+login_tbl+" where Name='"+username+"' and Password='"+password+"'",null);
        return cr;

    }
    public void backup() {
        try {
            File sdcard = Environment.getExternalStorageDirectory();
            File outputFile = new File(sdcard,
                    "WorkOut_Pro(New).db");

            if (!outputFile.exists())
                outputFile.createNewFile();

            File data = Environment.getDataDirectory();
            File inputFile = new File(data, "data/fast.community.fyp/databases/"+db_name);
            InputStream input = new FileInputStream(inputFile);
            OutputStream output = new FileOutputStream(outputFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            output.flush();
            output.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new Error("Copying Failed");
        }
    }
    public void Delete()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_name, null, null);
        db.delete(foodTable_name, null, null);
        db.delete(music_tbl, null, null);
        //db.close();
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
            antidots = ctx.getAssets().open("data.txt");
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
    public boolean InsertFromFile()  {
        String Name="Exercise Name",Secondary_Type="Secondary Muscle Target: ",Muscel_Type="Main Muscle Target",Tip="Start Point:See animation How to Start!", Motion="Motion: See animation motion!", Precaution="Caution: Follow The anime Exactly";
        int Gif;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        boolean bol=true;
        InputStream antidota = ctx.getResources().openRawResource(R.raw.data);//ctx.getResources().openRawResource(ctx.getResources().getIdentifier("data.txt","raw", ctx.getPackageName()));
        //InputStream antidots = new File(this.getResources().openRawResource(R.raw.data)); //<-- call getAssets on your Context object.
        try {
            //antidots = ctx.getAssets().open("file:///android_asset/data.txt");
            InputStreamReader input = new InputStreamReader(antidota);
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
                    Log.d("Hell in Gif parse======================>>>: ", line);

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
                        bol = false;
                    } else {
                        Log.d("Fish ===>>Message: ", "Correctly entered");
                        bol = true;
                        cv.clear();
                    }
            }} catch (IOException e) {
            e.printStackTrace();
        }
        return bol;
    }

    private File SD_PATH;
    private ArrayList<File> fileList = new ArrayList<File>();
    ArrayList<String> words = new ArrayList<String>();
    public void FetchFile() {
        try {
            InputStream antidota = ctx.getResources().openRawResource(R.raw.diet_data);//getResources().openRawResource(ctx.getResources().getIdentifier("data.txt","raw", ctx.getPackageName()));

            //<-- call getAssets on your Context object.
            //antidota = ctx.getAssets().open("diet_data.txt");
            InputStreamReader input = new InputStreamReader(antidota);
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
    public Cursor retrieveData(int range)
    {
        SQLiteDatabase sdata=this.getReadableDatabase();
        int upper=range+99;
        Cursor cr=sdata.rawQuery("select * from " + table_name + " where Gif BETWEEN " + range + " AND " + upper + "", null);
        return cr;
    }

    public void InputInDB()
    {
        FetchFile();
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
                result = db.insert(foodTable_name, null, cv);
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
        Cursor cr=sdata.rawQuery("select * from " + foodTable_name, null);
        return cr;
    }

    public Cursor retrieveFood(String food) {

        SQLiteDatabase sdata=this.getReadableDatabase();
        return sdata.rawQuery("select * from "+foodTable_name+ " where Food_Name = "+food,null);
    }
    public Cursor retrieveAllMusic()
    {
        SQLiteDatabase sdata=this.getReadableDatabase();
        Cursor cr=sdata.rawQuery("select * from " + music_tbl, null);
        return cr;
    }

    public boolean InsertMusic()
    {
        SD_PATH = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        getfile(SD_PATH);
        ContentValues cv=new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        boolean bol=false;
        for(int i=0;i<fileList.size();i++)
        {
            cv.put(music_title, fileList.get(i).getName());
            cv.put(music_path, fileList.get(i).getPath());
            long result = 0;
            try {
                result = db.insert(music_tbl, null, cv);
            } catch (Exception e) {
                result = 0;
            }
            if (result == -1) {
                Log.d("Fish >>>------------------===>>Message: Hell data error", "Musics in Database tAble -->> : "+fileList.get(i).getPath());
                bol = false;
            } else {
                Log.d("Fish >>------------------===>>>>>Message: ", "Correctly entered");
                bol = true;
                cv.clear();
            }
        }
        //db.close();
        return bol;
    }
    public Cursor retrievemusic(String music) {

        SQLiteDatabase sdata=this.getReadableDatabase();
        return sdata.rawQuery("select * from "+music_tbl+ " where Music_Title = "+music,null);
    }
    public ArrayList<File> getfile(File dir) {

        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory())
                    getfile(listFile[i]);
                else {
                    if (listFile[i].getPath().endsWith(".mp3")) {
                        fileList.add(listFile[i]);
                    }
                }

            }
        }
        return fileList;
    }
}
