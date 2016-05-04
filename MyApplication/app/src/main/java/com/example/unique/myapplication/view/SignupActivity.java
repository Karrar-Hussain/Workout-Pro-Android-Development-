package com.example.unique.myapplication.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unique.myapplication.R;
import com.example.unique.myapplication.model.Exercises_Tbl;


public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    EditText _nameText;
    EditText _emailText;
    EditText _passwordText;
    EditText _cpasswordText;
    Button _signupButton;
    TextView _loginLink;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    /*public void onBackPressed() {
        // Disable going back to the VideoPrediction
        moveTaskToBack(true);
    }
*/
    protected void onResume()
    {
        super.onResume();

    }
    protected void onPause()
    {
        super.onPause();

    }


    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this
                );
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        // TODO: Implement signup logic here just.


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        Exercises_Tbl obj=new Exercises_Tbl(this);

        boolean result=obj.Insert(_nameText.getText().toString(),_passwordText.getText().toString());
        obj.backup();
        if(result==true)
        {
            Intent intent = new Intent(getApplicationContext(), Profile.class);
            startActivity(intent);
        }
        else
            Toast.makeText(getBaseContext(),"Error In Inserting",Toast.LENGTH_LONG).show();



       // setResult(RESULT_OK, null);
        /*HashMap post=new HashMap();
        post.put("name", _nameText.getText().toString());
        post.put("username", _emailText.getText().toString());

        post.put("password", _passwordText.getText().toString());
        post.put("cpassword", _cpasswordText.getText().toString());
        post.put("phone", _phoneText.getText().toString());
        post.put("profession", _professionText.getText().toString());


        PostResponseAsyncTask task=new PostResponseAsyncTask(this,post);
        task.execute("http://community.softeezy.com/signupandroid.php");*/
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Account not Create", Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String cpassword=_cpasswordText.getText().toString();
        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("at least 4 to 10 characters");
            valid = false;
        }
        if (cpassword.isEmpty() || cpassword.length() < 4 || cpassword.length() > 10||!(password.equals(cpassword))) {
            _cpasswordText.setError("password mismatch");
            valid = false;
        }
        /*if (profession.isEmpty()) {
            _professionText.setError("Please Enter profession");
            valid = false;
        }
        else {
            _passwordText.setError(null);
        }

        if (phone.isEmpty()||phone.length()<11) {
            _phoneText.setError("Please Enter valid phone number");
            valid = false;}
            else if((phone.length()>11)&&!(phone.substring(0,2).equals("+92")))
        {
            valid=true;
        }

        else {
            _passwordText.setError(null);
        }*/

        return valid;
    }


}