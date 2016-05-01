package com.example.unique.myapplication.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unique.myapplication.R;
import com.example.unique.myapplication.control.Calculators;

/**
 * Created by Unique on 4/3/2016.
 */
public class IdealWeightActivity extends Activity {
    EditText txtAge,txtHeight,txtHeightcm,txtWeight;
    RadioButton rdMale,rdFemale;
    RadioGroup genderGrp;
    Button btnIBW;
    Double heightft,heightcm,bmi,weight;
    TextView viewIbw,viewbmr;
    int age;
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idealweight);

        genderGrp=(RadioGroup) findViewById(R.id.rdGrp);
        btnIBW=(Button) findViewById(R.id.btnIBW);
        txtHeight=(EditText) findViewById(R.id.txtHeight);
        txtHeightcm=(EditText)findViewById(R.id.txtHeightcm);
        rdFemale=(RadioButton) findViewById(R.id.rdFemale);
        rdMale=(RadioButton)findViewById(R.id.rdMale);
        viewIbw=(TextView) findViewById(R.id.viewIbw);
        viewbmr=(TextView) findViewById(R.id.viewbmr);
        txtAge=(EditText)findViewById(R.id.txtAge);
        txtWeight=(EditText) findViewById(R.id.txtWeight);

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
                if (!b) {
                    if (txtHeightcm.getText().toString().length() > 0) {
                        heightcm = (Double) Double.parseDouble(txtHeightcm.getText().toString());
                        heightft = 0.032808 * heightcm;
                        txtHeight.setText(heightft.toString());
                    }
                }

            }
        });
        btnIBW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (heightft > 0) {
                    Calculators cal = new Calculators();
                    heightft -= 5;
                    heightft = heightft * 10;
                    if (rdMale.isChecked())
                        heightft = 50 + 2.3 * heightft;
                    else
                        heightft = 45.5 + 2.3 * heightft;

                    weight= Double.parseDouble(txtWeight.getText().toString());
                    Double ibw=0.0;
                    bmi=cal.BMI_Cal(weight,heightcm);
                    //ibw = (.5 * bmi + 11.5) * heightft;
                    ibw=cal.IBW_Cal(bmi,heightft);
                    viewIbw.setText("Your IBW is " + ibw.toString() + " lbs & Ibw = " + heightft);
                    age = Integer.parseInt(txtAge.getText().toString());
                    boolean gender = true;
                    int selectedId = genderGrp.getCheckedRadioButtonId();

                    if (selectedId == -1)
                        Toast.makeText(IdealWeightActivity.this, "Please mention your gender", Toast.LENGTH_LONG).show();
                    else {
                        rdMale = (RadioButton) findViewById(selectedId);
                        String sex = rdMale.getText().toString();

                        if (sex.equals("Female"))
                            gender = false;
                        int bmr = (int) cal.BMR_Cal(age, weight, heightcm, gender);
                        //Toast.makeText(IdealWeightActivity.this,"AGe: "+age+" Weight: "+weight+" Heightcm :"+heightcm,Toast.LENGTH_LONG).show();
                        viewbmr.setText("Your Basal Metabolic rate (BMR): " + bmr);
                        //". You should atleast take "+bmr+" calories/day." );
                    }
                }
                }

            }

            );


        }

        @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}
