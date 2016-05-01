package com.example.unique.myapplication.view;

        import android.app.Activity;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.content.res.AssetManager;
        import android.os.Bundle;
        import android.util.Log;
        import android.webkit.WebView;
        import android.widget.Toast;

        import com.example.unique.myapplication.R;
        import com.example.unique.myapplication.model.Diet_Tbl;
        import com.example.unique.myapplication.model.Exercises_Tbl;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;

public class Splash extends Activity{
    WebView webView,webView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);


        final String PREFS_NAME = "MyPrefsFile";

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        webView=(WebView)findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/main_background.gif");
        //webView.getBackground().setAlpha(40);
        webView3=(WebView)findViewById(R.id.webView3);
        webView3.loadUrl("file:///android_asset/splash_loading.gif");
        Exercises_Tbl exercises_tbl;
        //Diet_Tbl diet_tbl;
        exercises_tbl=new Exercises_Tbl(Splash.this);

        if (settings.getBoolean("my_first_time", true)) {
            //the app is being launched for first time, do something
            Log.d("Comments", "First time");


            if(exercises_tbl.InsertMusic())
                if(exercises_tbl.Insert())
                if(exercises_tbl.InsertDummy(getAllGifs()))
                    Toast.makeText(Splash.this, "All Inserted Successfully!", Toast.LENGTH_LONG).show();
            exercises_tbl.InputInDB();
            // record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).commit();
        }
       /* else
        {
            exercises_tbl.Delete();
            if(exercises_tbl.Insert()&&exercises_tbl.InsertMusic())
                if(exercises_tbl.InsertDummy(getAllGifs()))
                    Toast.makeText(Splash.this, "Exercises Inserted Successfully!", Toast.LENGTH_LONG).show();
            exercises_tbl.InputInDB();
        }*/
        Thread timer=new Thread()
        {
            public void run()
            {
                try{
                    webView=(WebView)findViewById(R.id.webView);
//                    webView.loadUrl("file:///android_asset/main_background.gif");
                    sleep(7000);
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

    public List getAllGifs()
    {
        List<String> ex_list = new ArrayList<String>();
        final AssetManager assetManager = getAssets();
        try {
            // for assets folder add empty string
            String[] filelist = assetManager.list("Gifs");
            String gifstr;
            // for assets/subFolderInAssets add only subfolder name
            if (filelist == null) {
                // dir does not exist or is not a directory
            } else {
                for (int i=0; i<filelist.length; i++) {
                    // Get filename of file or directory
                    gifstr=filelist[i];
                    //if(gifstr.contains(".gif"))
                    ex_list.add( gifstr);
                }
            }
            // if(filelistInSubfolder == null) ............

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ex_list;
    }
}
