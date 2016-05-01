package com.example.unique.myapplication.view;

import android.app.ListActivity;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.ActionMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Toast;

import com.example.unique.myapplication.R;
import com.example.unique.myapplication.model.Exercises_Tbl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicPlayerActivity extends ListActivity{

    ListView viewList;
    List<String> songs=new ArrayList<String>();
    private MediaPlayer mp=new MediaPlayer();
    private ArrayList<String> fileList = new ArrayList<String>();
    Button btnPause,btnForward,btnBackward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        //viewList =(ListView) findViewById(R.id.musicList);

        getMusicList();
        //viewList.setAdapter(songList);
        //Toast.makeText(this,"SD Card: "+SD_PATH,Toast.LENGTH_LONG).show();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
            if(currentPosition<fileList.size())
                playNext();
            }
        });
        btnBackward=(Button) findViewById(R.id.btnBackward);
        btnForward=(Button) findViewById(R.id.btnForward);
        btnPause=(Button)findViewById(R.id.btnPlay);
        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           playNext();
            }
        });
        btnBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playPrev();
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying()) {
                    btnPause.setBackgroundResource(R.drawable.play);
                    mp.pause();
                }else
                {
                    btnPause.setBackgroundResource(R.drawable.pause);
                    mp.start();
                }
            }
        });

        if(fileList!=null)
        {
            if(fileList.size()>0) {
                try {
                    mp.reset();
                    mp.setDataSource(fileList.get(currentPosition));
                    mp.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Button btnStop=(Button) findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
            }
        });
    }

    public void getMusicList()
    {
        getfile();
        ArrayAdapter<String> songList = new ArrayAdapter<String>(this, R.layout.music_textview, songs);
        setListAdapter(songList);
    }
    public void getfile() {
        Exercises_Tbl exercises_tbl=new Exercises_Tbl(this);
        Cursor cr=exercises_tbl.retrieveAllMusic();
        cr.moveToFirst();
        while(cr.moveToNext())
        {
            songs.add(cr.getString(cr.getColumnIndex("Music_Title")));
            fileList.add(cr.getString(cr.getColumnIndex("Music_Path")));

        }

    }
    private int currentPosition=0;

    private void playPrev()
    {
        if(currentPosition>0)
        {
            try {
                mp.reset();
                mp.setDataSource(fileList.get(--currentPosition));
                mp.prepare();
                mp.start();
                Toast.makeText(this,""+songs.get(currentPosition),Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void playNext()
    {
        try {
            mp.reset();
            mp.setDataSource(fileList.get(++currentPosition));
            mp.prepare();
            mp.start();
            Toast.makeText(this,""+songs.get(currentPosition),Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        try{
            btnPause.setBackgroundResource(R.drawable.pause);
            currentPosition=position;
            mp.reset();
            mp.setDataSource(fileList.get(currentPosition));
            mp.prepare();
            mp.start();
            Toast.makeText(this,"Now Playing: "+songs.get(currentPosition)+" ...",Toast.LENGTH_SHORT).show();
        }catch(IOException e){
            e.getStackTrace();
        }
    }

}
