package com.example.unique.myapplication.view;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unique.myapplication.R;
import com.example.unique.myapplication.model.Exercises_Tbl;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Unique on 4/3/2016.
 */
public class WorkoutActivity extends Activity implements AdapterView.OnItemSelectedListener {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    String url;
    int value;
    ListView listView;
    List gifList,nameList,mainMuscleList,secMuscleList,precautionList,startPntList,motionList;
    WebView webView2;
    Button btnYoutube;
    TextView viewExName,viewMainMuscle,viewSecMuscle,viewStartPnt,viewMotion,viewPrecaution;
    Exercises_Tbl db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        webView2=(WebView)findViewById(R.id.webVGifs);
        viewExName=(TextView)findViewById(R.id.viewExName);
        viewMainMuscle=(TextView)findViewById(R.id.viewMainMuscle);
        viewSecMuscle=(TextView)findViewById(R.id.viewSecondaryMuscle);
        viewStartPnt=(TextView)findViewById(R.id.viewStartPnt);
        viewMotion=(TextView)findViewById(R.id.viewMotion);
        viewPrecaution=(TextView)findViewById(R.id.viewPrecaution);
          btnYoutube=(Button)findViewById(R.id.btnYoutube);
        btnYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), GymExercises_YT_VidActivity.class));
            }
        });
        url="file:///android_asset/Gifs/200.gif";
        webView2.loadUrl(url);
        Spinner spinner = (Spinner) findViewById(R.id.ex_spinner);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        List<String> ex_list = new ArrayList<String>();

        Bundle b = getIntent().getExtras();
        value = b.getInt("key");
        //ex_list=getWorkoutInfo(value);
         /////////////////////////////////
        //Diet_Tbl wia=new Diet_Tbl(this);
        //wia.FetchFile();
        /////////////////////////////////
        db=new Exercises_Tbl(this);
        Cursor cr=db.retrieveData(value);
        //db.close();
        cr.moveToFirst();
        gifList=new ArrayList<String>();
        nameList=new ArrayList<String>();
        mainMuscleList=new ArrayList<String>();
        secMuscleList=new ArrayList<String>();
        startPntList=new ArrayList<String>();
        motionList=new ArrayList<String>();
        precautionList=new ArrayList<String>();
        String gif,name;
            while (cr.moveToNext()) {
                gif = cr.getString(Integer.valueOf(cr.getColumnIndex("Gif")));
                name=String.valueOf(cr.getString(cr.getColumnIndex("Name")));
                nameList.add(name);
                name=String.valueOf(cr.getString(cr.getColumnIndex("Muscle_Type")));
                mainMuscleList.add(name);
                //Log.d("One Pice Luffy===>>> Gum! Gum! Gum!:gif, name, musle, musle2, start, motion,tip ==>> ", "Hele" + gif + " " + name);
                name=String.valueOf(cr.getString(cr.getColumnIndex("Secondary_Type")));
                secMuscleList.add(name);
                //Log.d("One Pice Luffy===>>> Gum! Gum! Gum!:gif, name, musle, musle2, start, motion,tip ==>> ", "Hele" + gif + " " + name);
                name=String.valueOf(cr.getString(cr.getColumnIndex("Tip")));
                startPntList.add(name);
                //Log.d("One Pice Luffy===>>> Gum! Gum! Gum!:gif, name, musle, musle2, start, motion,tip ==>> ", "Hele" + gif + " " + name);
                name=String.valueOf(cr.getString(cr.getColumnIndex("Motion")));
                motionList.add(name);
                //Log.d("One Pice Luffy===>>> Gum! Gum! Gum!:gif, name, musle, musle2, start, motion,tip ==>> ", "Hele" + gif + " " + name);
                name=String.valueOf(cr.getString(cr.getColumnIndex("Precaution")));
                precautionList.add(name);
                gif = gif + ".gif";
                gifList.add(gif);
                //Log.d("One Pice Luffy===>>> Gum! Gum! Gum!:gif, name, musle, musle2, start, motion,tip ==>> ","Hele"+gif+" "+name);
            }

        ex_list = nameList;
        /////////////////////////////////
        ArrayAdapter<String> ex_adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,ex_list);
        ex_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(ex_adapter);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        int index=1;

        index=nameList.indexOf(item);
        url="file:///android_asset/Gifs/"+gifList.get(index);
        webView2.loadUrl(url);
        viewExName.setText(item);
        viewMainMuscle.setText(String.valueOf(mainMuscleList.get(index)));
        viewSecMuscle.setText(String.valueOf(secMuscleList.get(index)));
        viewStartPnt.setText(String.valueOf(startPntList.get(index)));
        viewMotion.setText(String.valueOf(motionList.get(index)));
        viewPrecaution.setText(String.valueOf(precautionList.get(index)));
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
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
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        //finish();
    }

}