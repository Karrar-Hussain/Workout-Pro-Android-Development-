package com.example.unique.myapplication.view;

import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.unique.myapplication.R;
import com.example.unique.myapplication.model.Exercises_Tbl;
import com.google.android.youtube.player.YouTubePlayer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Handler handler;
    private ArrayList<VideoItem> vid=new ArrayList<VideoItem>();

    List nameList,mainMuscleList,ex_list;
    private CustomAdapter adapter;
    private ListView lView;
    Exercises_Tbl db;
    Spinner ex_spinner;
    private int value;
    YoutubeConnector yc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.ex_list);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        List<String> ex_list = new ArrayList<String>();

        Bundle b = getIntent().getExtras();
        value = b.getInt("key");

        db=new Exercises_Tbl(this);
        Cursor cr=db.retrieveData(value);
        //db.close();
        cr.moveToFirst();
        nameList=new ArrayList<String>();
        mainMuscleList=new ArrayList<String>();

        String name;
        while (cr.moveToNext()) {
            name = String.valueOf(cr.getString(cr.getColumnIndex("Name")));
            nameList.add(name);
            name = String.valueOf(cr.getString(cr.getColumnIndex("Muscle_Type")));
            mainMuscleList.add(name);
        }
        ex_list = nameList;
        /////////////////////////////////
        ArrayAdapter<String> ex_adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,ex_list);
        ex_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(ex_adapter);


        handler=new Handler();
        lView= (ListView)findViewById(R.id.videos);
        yc=new YoutubeConnector(MainActivity.this);

        addClickListener();

    }

    private void addClickListener(){
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> av, View v, int i, long id) {
                Intent intent = new Intent(MainActivity.this, YoutubePlayer.class);
                intent.putExtra("VIDEO_ID", vid.get(i).getId());
                startActivity(intent);
            }

        });
    }
    String searchEx="Chest";
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        int index=1;
        index=nameList.indexOf(item);
        searchEx="Gym Workout Muscle Type "+mainMuscleList.get(index).toString()+" "+nameList.get(index).toString();
        // Showing selected spinner item
        new Thread(){
            public void run(){
                vid=yc.search(searchEx);
                adapter = new CustomAdapter(vid,MainActivity.this);
                handler.post(new Runnable(){
                    public void run(){
                        lView.setAdapter(adapter);
                    }
                });
            }
        }.start();
        Toast.makeText(parent.getContext(), "Selected: " +searchEx, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
