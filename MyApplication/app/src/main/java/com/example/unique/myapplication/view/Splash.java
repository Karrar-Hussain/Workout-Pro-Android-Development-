package com.example.unique.myapplication.view;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;

        import com.example.unique.myapplication.R;

public class Splash extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);
        Thread timer=new Thread()
        {
            public void run()
            {
                try{
                    sleep(3000);
                }catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {

                    Intent start=new Intent("com.example.unique.myapplication.NAVIGATIONACTIVITY");
                    //Intent start=new Intent("com.example.unique.myapplication.IDEALWEIGHTACTIVITY");
                    startActivity(start);
                }
            }

        };
        timer.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

}
