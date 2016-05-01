package com.example.unique.myapplication.view;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.unique.myapplication.R;
import com.example.unique.myapplication.control.Diet_Scheduler;
import com.example.unique.myapplication.model.Exercises_Tbl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DietActivity extends ListActivity{

    ListView viewList;
    List<String> food_list=new ArrayList<String>();
    Button btnDiet;
    WebView webView2;
    //private File SD_PATH;// = Environment.getExternalStorageDirectory().getPath() + "/";
    //private static final String SD_PATH =new String("/sdcard/");
    //private MediaPlayer mp=new MediaPlayer();
    private ArrayList<File> fileList = new ArrayList<File>();
    //Button btnPause,btnForward,btnBackward;

    Exercises_Tbl db;
    Diet_Scheduler diet_scheduler;
    List food_name,food_type,unit,calories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_selection);

        webView2 =(WebView) findViewById(R.id.webView2);
        webView2.loadUrl("file:///android_asset/food_background.gif");
        db=new Exercises_Tbl(this);
        btnDiet=(Button) findViewById(R.id.btnDiet);
        diet_scheduler=new Diet_Scheduler();
        btnDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DietSchedulerActivity.class);
                Bundle b = new Bundle();
                ArrayList<String> myList = diet_scheduler.makeSchedule(food_list);
                ;
                intent.putExtra("mylist", myList);
                startActivity(intent);
            }
        });
        food_name=new ArrayList<String>();
        food_type=new ArrayList<String>();
        unit=new ArrayList<String>();
        calories=new ArrayList<String>();
        getFoodList();
    }


    public void getFoodList()
    {
        Cursor cr=db.retrieveAllFoods();
        cr.moveToFirst();
        while(cr.moveToNext())
        {
            food_name.add(cr.getString(cr.getColumnIndex("Food_Name")));
            food_type.add(cr.getString(cr.getColumnIndex("Food_Type")));
            unit.add(cr.getString(cr.getColumnIndex("Unit")));
            calories.add(cr.getString(cr.getColumnIndex("Calories")));
        }
        /*for(int i=0;i<food_name.size();i++)
        {
            food_list.add(food_name.get(i)+" : "+food_type.get(i)+" : "+unit.get(i)+" : "+calories.get(i));
        }*/
        ArrayAdapter<String> foodList = new ArrayAdapter<String>(this, R.layout.music_textview, food_name);
        setListAdapter(foodList);
        db.close();
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
            food_list.add((String) food_name.get(position) + "\t" + (String) food_type.get(position) + "\t" + (String) unit.get(position) + "\t" + (String) calories.get(position));
        Toast.makeText(this,food_name.get(position)+" is select from "+food_type.get(position)+" category.",Toast.LENGTH_SHORT).show();
    }

}
