package com.example.unique.myapplication.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.example.unique.myapplication.R;
import com.example.unique.myapplication.model.Exercises_Tbl;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity {
    Exercises_Tbl obj;
    VideoPrediction main;

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private SharedPreferences sharedPreferences;

    public static final String MyPREFERENCES = "MyPrefs" ;

    EditText username1,pass1;
    Button login;
    public static final String name1="Name",password1="Password";

    public static String name,email,phone,profession,distance;
    public static int row_count;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username1=(EditText)findViewById(R.id.input_email);    //Casting controls from front view to workable fields
        pass1=(EditText)findViewById(R.id.input_password);
        login=(Button)findViewById(R.id.btn_login);
        obj=new Exercises_Tbl(this);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        //ButterKnife.bind(this);
        //login.setOnClickListener(this);
        //_signupLink.setOnClickListener(this);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AddData();   //Validating and Adding the data........

            }
        });

    }

    public void AddData()
    {
        //obj.CreateTables();

        boolean result=validate();
        if(result==true)
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Name",username1.getText().toString());
            editor.putString("Password",pass1.getText().toString());
            editor.commit();

            Cursor CR=obj.loginData(username1.getText().toString(),pass1.getText().toString());
            if(CR.moveToNext())
            {
                Toast.makeText(LoginActivity.this,"Login Sucessfull",Toast.LENGTH_LONG).show();
                Intent intent=new Intent("fast.community.se.MainPage");
                intent.putExtra("UserName",username1.getText().toString());
                startActivity(intent);
                obj.backup();
            }
            else
                Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_LONG).show();
            //boolean res=obj.Insert(username1.getText().toString() ,pass1.getText().toString());

//            if(res==true)
//            {
//
//            }
//            else
//                Toast.makeText(LoginActivity.this,"Data is not inserted",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(LoginActivity.this,"Wrong Credentials",Toast.LENGTH_LONG).show();
        }
    }




    /*@Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {
    login();
        } else if (v.getId() == R.id.link_signup) {

            Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
            startActivityForResult(intent, REQUEST_SIGNUP);

        }
    }*/
    /*public void processFinish(String s) {
        int i=s.indexOf("+");
        name=s.substring(0, i - 1);
        String temp=s.substring(i + 1);
        i=temp.indexOf("+");
        email=temp.substring(0, i - 1);
        temp=temp.substring(i + 1);
        i=temp.indexOf("+");
        phone=temp.substring(0, i - 1);

        temp=temp.substring(i + 1);
        i=temp.indexOf("+");
        profession=temp.substring(0, i - 1);

        temp=temp.substring(i + 1);
        i=temp.indexOf("+");
        distance=temp.substring(0,i-1);

       int counter = 0;
        for(i=0; i<name.length(); i++ ) {
            if( name.charAt(i) == ',' ) {
                counter++;
            }
        }
         row_count=counter;
         if(s.equals("no"))
        {
            onLoginFailed();
            }
        else
        {
            Toast.makeText(getBaseContext(),"Login Success",Toast.LENGTH_LONG).show();
            Intent log = new Intent(LoginActivity.this, RecyclerMainActivity.class);
            this.startActivity(log);

        }
    }
*/

    //public boolean login(String email,String pass) {
        //Log.d(TAG, "Login");

        //if (!validate()) {
          //  onLoginFailed();
            //return;
        //}

        //login.setEnabled(false);

        //final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this
       // );
        //progressDialog.setIndeterminate(true);
        //progressDialog.setMessage("Authenticating...");
        //progressDialog.show();



        //if (email == null) {
          //  Toast.makeText(LoginActivity.this,"Invalid Email",Toast.LENGTH_LONG).show();
            //return false;
        //} else {
          //  return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        //}

        // TODO: Implement  authentication logic here just...!!

        //new android.os.Handler().postDelayed(
          //      new Runnable() {
            //        public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        //onLoginSuccess();
                        // onLoginFailed();
              //          progressDialog.dismiss();
                //    }
                //}, 3000);

    //}

/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                Intent log = new Intent(LoginActivity.this, VideoPrediction.class);
                this.startActivity(log);
                // TODO: Yaha pe  successful signup logic Implement kar de bs..
                // this.finish ko hata daen logic implement kar ke..!!
            }
        }

    }
    //@Override
   /* public void onBackPressed() {
        // Disable going back to the VideoPrediction
        moveTaskToBack(true);
    }
    protected void onResume()
    {
        super.onResume();
        overridePendingTransition(R.layout.right_in, R.layout.static_anim);
    }
    protected void onPause()
    {
        super.onPause();
        overridePendingTransition(R.layout.static_anim, R.layout.left_out);
    }

    /*public void onLoginSuccess() {
        HashMap post = new HashMap();
        post.put("username", _emailText.getText().toString());
        post.put("password", _passwordText.getText().toString());
        PostResponseAsyncTask pResponse = new PostResponseAsyncTask(this, post);
        pResponse.execute("http://community.softeezy.com/loginandroid.php");
        _loginButton.setEnabled(true);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }
*/
    public boolean validate() {
        boolean valid = true;

        String email = username1.getText().toString();
        String password = pass1.getText().toString();

        if (email.isEmpty() || username1.length()<3) {
            username1.setError("Enter a valid User Name");
            valid = false;
        } else {
            username1.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            pass1.setError("Enter Valid password ");
            valid = false;
        } else {
            pass1.setError(null);
        }

        return valid;
    }


}