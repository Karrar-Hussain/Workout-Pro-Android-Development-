package com.example.unique.myapplication.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.unique.myapplication.R;

/**
 * Created by Unique on 4/4/2016.
 */
public class GymExercisesActivity extends Activity {
        /** Called when the activity is first created. */
        Button viewForearm,viewAbs,viewShoulder,viewChest,viewBicep,viewTricep,viewLegs,viewCalve;
        Button viewBack;
    String path="file:///android_asset/Gifs/730.gif";
        @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_gymexercise);
// setting the images on the ImageViews
            viewAbs=(Button) findViewById(R.id.viewAbs);
            viewBicep=(Button) findViewById(R.id.viewBicep);
            viewTricep=(Button) findViewById(R.id.viewTricep);
            viewShoulder=(Button) findViewById(R.id.viewShoulder);
            viewLegs=(Button) findViewById(R.id.viewLegs);
            viewChest=(Button) findViewById(R.id.viewChest);
            viewCalve=(Button) findViewById(R.id.viewCalve);
            viewForearm=(Button) findViewById(R.id.viewForearem);
            viewBack=(Button) findViewById(R.id.viewBack);
            viewBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),WorkoutActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("key", 200); //Your id
                    intent.putExtras(b); //Put your id to your next Intent
                    startActivity(intent);
                }
            });
            viewAbs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),WorkoutActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("key", 100); //Your id
                    intent.putExtras(b); //Put your id to your next Intent
                    startActivity(intent);
                }
            });
            viewBicep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),WorkoutActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("key", 700); //Your id
                    intent.putExtras(b); //Put your id to your next Intent
                    startActivity(intent);
                }
            });
            viewTricep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),WorkoutActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("key", 800); //Your id
                    intent.putExtras(b); //Put your id to your next Intent
                    startActivity(intent);
                }
            });
            viewShoulder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),WorkoutActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("key", 400); //Your id
                    intent.putExtras(b); //Put your id to your next Intent
                    startActivity(intent);
                }
            });
            viewLegs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),WorkoutActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("key", 500); //Your id
                    intent.putExtras(b); //Put your id to your next Intent
                    startActivity(intent);
                }
            });
            viewChest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),WorkoutActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("key", 300); //Your id
                    intent.putExtras(b); //Put your id to your next Intent
                    startActivity(intent);
                }
            });
            viewCalve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),WorkoutActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("key", 600); //Your id
                    intent.putExtras(b); //Put your id to your next Intent
                    startActivity(intent);
                }
            });
            viewForearm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),WorkoutActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("key", 900); //Your id
                    intent.putExtras(b); //Put your id to your next Intent
                    startActivity(intent);
                }
            });


        }

}
