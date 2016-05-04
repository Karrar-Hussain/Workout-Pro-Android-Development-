package com.example.unique.myapplication.view;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.unique.myapplication.R;
import com.example.unique.myapplication.control.Diet_Scheduler;
import com.example.unique.myapplication.model.Exercises_Tbl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DietActivity extends ListActivity implements AdapterView.OnItemSelectedListener {

    List<String> food_list=new ArrayList<String>();
    Button btnDiet;
    Spinner ddworkout,ddGoals;
    //private File SD_PATH;// = Environment.getExternalStorageDirectory().getPath() + "/";
    //private static final String SD_PATH =new String("/sdcard/");
    //private MediaPlayer mp=new MediaPlayer();
    private ArrayList<File> fileList = new ArrayList<File>();
    //Button btnPause,btnForward,btnBackward;

    double keyworkout=1;
    int keygoal=1;
    Exercises_Tbl db;
    Diet_Scheduler diet_scheduler;
    List food_name,food_type,unit,calories,ex_list,goal_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_selection);

        ddGoals=(Spinner) findViewById(R.id.ddGoals);
        ddworkout=(Spinner) findViewById(R.id.ddWorkout);
        db=new Exercises_Tbl(this);
        btnDiet=(Button) findViewById(R.id.btnDiet);
        diet_scheduler=new Diet_Scheduler();
        btnDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DietSchedulerActivity.class);
                Bundle b = new Bundle();
                ArrayList<String> myList = diet_scheduler.makeSchedule(food_list,keyworkout,keygoal);
                //intent.putExtra("key_workout",keyworkout);
                intent.putExtra("mylist", myList);
                startActivity(intent);
            }
        });

        ex_list=new ArrayList<String>();
        goal_list=new ArrayList<String>();

        ex_list.add("Basal Metabolic Rate (BMR)");
        ex_list.add("Little or no exercise");
        ex_list.add("Lightly active –exercise/sports 1-3 times/week");
        ex_list.add("Moderately active –exercise/sports 3-5 times/week");
        ex_list.add("Very active –hard exercise/sports 6-7 times/week");
        ex_list.add("Extra active –very hard exercise/sports or physical job");

        goal_list.add("Maintain your Current weight");
        goal_list.add("Lose 1lb per week");
        goal_list.add("Lose 2lb per week");
        goal_list.add("Gain 1lb per week");
        goal_list.add("Gain 2lb per week");

        ArrayAdapter<String> goal_adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,goal_list);
        goal_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddGoals.setAdapter(goal_adapter);

        ArrayAdapter<String> ex_adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,ex_list);
        ex_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddworkout.setAdapter(ex_adapter);

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.ddGoals)
        {
            keygoal=position;
        }
        else if(spinner.getId() == R.id.ddWorkout)
        {
            if(position==0)
            {
                keyworkout=1;
            }else if(position==1)
            {
                keyworkout=1.2;
            }else if(position==2)
            {
                keyworkout=1.375;
            }else if(position==3)
            {
                keyworkout=1.55;
            }else if(position==4)
            {
                keyworkout=1.724;
            }else if(position==5)
            {
                keyworkout=1.9;
            }   //do this
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
