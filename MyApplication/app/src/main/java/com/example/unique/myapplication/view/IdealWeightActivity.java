package com.example.unique.myapplication.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.unique.myapplication.R;

/**
 * Created by Unique on 4/3/2016.
 */
public class IdealWeightActivity extends Activity {
    EditText txtAge,txtHeight,txtHeightcm;
    RadioButton rdMale,rdFemale;
    Button btnIBW;
    Double heightft,heightcm,bmi;
    TextView viewIbw;
    int age;
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idealweight);

        btnIBW=(Button) findViewById(R.id.btnIBW);
        txtHeight=(EditText) findViewById(R.id.txtHeight);
        txtHeightcm=(EditText)findViewById(R.id.txtHeightcm);
        rdFemale=(RadioButton) findViewById(R.id.rdFemale);
        rdMale=(RadioButton)findViewById(R.id.rdMale);
        viewIbw=(TextView) findViewById(R.id.viewIbw);
        txtHeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    if(txtHeight.getText().toString().length()>0)
                    {
                        heightft=(Double) Double.parseDouble(txtHeight.getText().toString());
                        heightcm=30.48 * heightft;
                        txtHeightcm.setText(heightcm.toString());
                    }
                }

            }
        });
        txtHeightcm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    if(txtHeightcm.getText().toString().length()>0)
                    {
                        heightcm=(Double) Double.parseDouble(txtHeightcm.getText().toString());
                        heightft=.39 * heightcm;
                        txtHeight.setText(heightft.toString());
                    }
                }

            }
        });
        btnIBW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if(heightft>0)
                    {
                        bmi=18.8;
                        Double ibw;
                        ibw=(.5*bmi+11.5)*heightft;
                        heightft-=5;
                        heightft=heightft*10;
                        if(rdMale.isChecked())
                        heightft=50+2.3*heightft;
                        else
                        heightft=45.5+2.3*heightft;
                        viewIbw.setText("Your IBW is "+ibw.toString()+" lbs & Ibw = "+heightft);
                    }
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
