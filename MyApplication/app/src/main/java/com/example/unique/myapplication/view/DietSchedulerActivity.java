package com.example.unique.myapplication.view;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.unique.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Unique on 4/28/2016.
 */
public class DietSchedulerActivity extends ListActivity {
   // ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet_schedule);
       // listView=(ListView) findViewById(R.id.foodList);
        ArrayList<String> myList = (ArrayList<String>) getIntent().getSerializableExtra("mylist");
        //List foodlist= d.getStringArrayList("arrkey");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.music_textview,myList);
        setListAdapter(arrayAdapter);
    }
}
