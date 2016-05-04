package com.example.unique.myapplication.view;

//import com.sonymobile.stepcounter.R;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unique.myapplication.R;

public class Main extends Activity implements SensorEventListener {

    private float mStepSinceBoot, mLastStepReport, mCurrSteps;
    private boolean mInitialized;
    private SensorManager mSensorManager;
    private Sensor mStepCounter;

    /**
     * Called when the activity is first created.
     * The step counter sensor listener is registered upon creation
     * and should be registered until the activity is destroyed.
     * <p/>
     * The other activity call-back functions are not needed and
     * left empty in this implemented. This is done just to emphasize
     * that no active power management is needed when using the
     * built-in step counter.
     * <p/>
     * The sensor co-processor will detect when the system goes down
     * and continue to track steps without evoking the application
     * processor. As soon as the co-processor detects that the system
     * goes up again, a new sensor event will be sent if there were any
     * new steps detected while system-down.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mInitialized = false;
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mStepCounter = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mStepCounter, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // can be safely ignored for this demo
    }

    /**
     * by definition the step counter sensor type starts from zero
     * upon system boot and doesn't reset its value for new registered
     * listeners. This means that each application/client needs to cope
     * with this according to its needs.
     * <p/>
     * In this particular implementation it has been chosen to reset the
     * step counts upon application activation, hence the offset
     * tracking below.
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        TextView startOffset = (TextView) findViewById(R.id.steps_since_boot);
        TextView lastReport = (TextView) findViewById(R.id.last_step_report);
        TextView currSteps = (TextView) findViewById(R.id.current_steps);

        mLastStepReport = event.values[0];
        if (!mInitialized) {
            mStepSinceBoot = mLastStepReport;
            mCurrSteps = 0;
            startOffset.setText(Float.toString(mStepSinceBoot));
            lastReport.setText(Float.toString(mLastStepReport));
            currSteps.setText("0.0");
            mInitialized = true;
        } else {
            mCurrSteps = (mLastStepReport - mStepSinceBoot);
            lastReport.setText(Float.toString(mLastStepReport));
            currSteps.setText(Float.toString(mCurrSteps));
        }
    }
}