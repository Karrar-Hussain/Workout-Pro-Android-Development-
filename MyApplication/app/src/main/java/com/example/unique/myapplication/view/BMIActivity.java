package com.example.unique.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.unique.myapplication.R;

/**
 * A login screen that offers login via email/password.
 */
public class BMIActivity extends Activity {

    TextView viewbmi, viewcnpd;
    EditText  heighttxtft, weighttxtkg, heighttxtcm, weighttxtlbs;
    Button btnBMI,btnIW;
    Double heightft, weightft, bmi, ideal_w, cal_per_day;
    Double weightpds, heightcm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        heighttxtft = (EditText) findViewById(R.id.txtHeightft);
        weighttxtkg = (EditText) findViewById(R.id.txtweightkg);
        heighttxtcm = (EditText) findViewById(R.id.txtHeightcm);
        weighttxtlbs = (EditText) findViewById(R.id.txtWeightlbs);
        btnBMI = (Button) findViewById(R.id.btnBMI);
        viewbmi = (TextView) findViewById(R.id.viewBMI);
        viewcnpd = (TextView) findViewById(R.id.viewCNPD);
        btnIW=(Button) findViewById(R.id.btnIW);
        heighttxtft.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                {
                    heightft = Double.parseDouble(heighttxtft.getText().toString());
                    heightcm = 30.48 * heightft;
                    heighttxtcm.setText(heightcm.toString());
                }
            }
        });
        heighttxtcm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    heightcm = Double.parseDouble(heighttxtcm.getText().toString());
                    heightft = .39 * heightcm;
                    heighttxtft.setText(heightft.toString());
                }
            }
        });
        weighttxtlbs.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    weightpds = Double.parseDouble(weighttxtlbs.getText().toString());
                    weightft = .45 * weightpds;
                    weighttxtkg.setText(weightft.toString());
                }
            }
        });

        weighttxtkg.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    weightft = Double.parseDouble(weighttxtkg.getText().toString());
                    weightpds = 2.2 * weightft;
                    weighttxtlbs.setText(weightpds.toString());
                }
            }
        });

        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                heightcm /= 100;
                bmi = weightft / (heightcm * heightcm);
                ideal_w = (((0.5 * bmi) + 11.5) * heightcm * heightcm);
                viewbmi.setText(String.format("Your BMI : %.2f", bmi));
                viewcnpd.setText(String.format("Ideal Weight :%.2f ", ideal_w));
            }
        });
        btnIW.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),IdealWeightActivity.class));
            }
        });
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}

