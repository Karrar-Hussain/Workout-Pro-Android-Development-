package com.example.unique.myapplication.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.unique.myapplication.R;

public class MainBackground extends Activity{

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_background);
        //webView=(WebView)findViewById(R.id.webView_mainbackground);
        //webView.loadUrl("file:///android_asset/music_background.gif");
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

}
