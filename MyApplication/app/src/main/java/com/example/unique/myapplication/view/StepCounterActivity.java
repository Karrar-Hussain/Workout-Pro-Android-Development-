package com.example.unique.myapplication.view;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unique.myapplication.R;

public class StepCounterActivity extends Activity implements SensorEventListener{

    TextView viewSteps;
    SensorManager sensorManager;
    Boolean activityRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);
        viewSteps=(TextView) findViewById(R.id.viewSteps);
        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityRunning=true;
        Sensor countSensor=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor !=null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        }else{
            Toast.makeText(this,"Counter Sensor is Not Available!",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityRunning=false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
    if(activityRunning){
        viewSteps.setText(String.valueOf(event.values[0]));
    }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
