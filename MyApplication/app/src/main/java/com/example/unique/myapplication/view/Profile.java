package com.example.unique.myapplication.view;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.unique.myapplication.R;
import com.example.unique.myapplication.model.Exercises_Tbl;


public class Profile extends ActionBarActivity {

    Exercises_Tbl obj;

    public String array_spiner[];
    public String array_spiner1[];
    public EditText name;
    public Spinner occ_type;
    public Spinner goal_type;
    public EditText height;
    public EditText weight;
    public EditText pay;
    public EditText age;
    public Button make_profile;

    int occ_type_int;
    int goal_type_int;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        array_spiner=new String[4];
        array_spiner[0]="Labour Work";
        array_spiner[1]="Office Work";
        array_spiner[2]="Sports and Games";
        array_spiner[3]="Student";
        Spinner s = (Spinner) findViewById(R.id.occu_type_spinner);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_spiner);
        s.setAdapter(adapter);

        array_spiner1=new String[3];
        array_spiner1[0]="Lose Weight";
        array_spiner1[1]="Gain Weight";
        array_spiner1[2]="Maintain Weight";
        Spinner s1 = (Spinner) findViewById(R.id.goal_type_spinner);
        ArrayAdapter adapter1 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_spiner1);
        s1.setAdapter(adapter1);

        name=(EditText)findViewById(R.id.input_name);
        age=(EditText)findViewById(R.id.input_age);
        occ_type=(Spinner)findViewById(R.id.occu_type_spinner);
        goal_type=(Spinner)findViewById(R.id.goal_type_spinner);
        height=(EditText)findViewById(R.id.input_height);
        weight=(EditText)findViewById(R.id.input_wieght);
        pay=(EditText)findViewById(R.id.input_pay);
        make_profile=(Button)findViewById(R.id.make_pro_button);
        make_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AddData()==true)
                {
                    Toast.makeText(getBaseContext(), "Profile Sucessfully Made", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getBaseContext(), "Profile Not Created", Toast.LENGTH_LONG).show();

            }
        });



    }

    public boolean AddData()
    {
        obj=new Exercises_Tbl(this);
        if(occ_type.getSelectedItem().toString()=="Labour Work")
        {
            occ_type_int=0;
        }
        else if(occ_type.getSelectedItem().toString()=="Office Work")
        {
            occ_type_int=1;
        }
        else if(occ_type.getSelectedItem().toString()=="Sports and Games")
        {
            occ_type_int=2;
        }
        else if(occ_type.getSelectedItem().toString()=="Student")
        {
            occ_type_int=3;
        }
        else
            occ_type_int=100;

        if(goal_type.getSelectedItem().toString()=="Lose Weight")
        {
            goal_type_int=0;
        }
        else if(goal_type.getSelectedItem().toString()=="Gain Weight")
        {
            goal_type_int=1;
        }
        else if(goal_type.getSelectedItem().toString()=="Maintain Weight")
        {
            goal_type_int=2;
        }
        else
            goal_type_int=100;

        boolean result=obj.InsertIntoPerson(name.getText().toString(),Integer.parseInt(age.getText().toString()) ,occ_type_int,goal_type_int,Float.parseFloat(height.getText().toString()),Float.parseFloat(weight.getText().toString()),Integer.parseInt(pay.getText().toString()));
        obj.backup();
        if(result==true)
            return true;
        else
            return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
