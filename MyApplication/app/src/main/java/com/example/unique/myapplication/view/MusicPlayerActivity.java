package com.example.unique.myapplication.view;

import android.app.ListActivity;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
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

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MusicPlayerActivity extends ListActivity implements MediaController.MediaPlayerControl{

    ListView viewList;
    List<String> songs=new ArrayList<String>();
    private File SD_PATH;// = Environment.getExternalStorageDirectory().getPath() + "/";
    //private static final String SD_PATH =new String("/sdcard/");
    private MediaPlayer mp=new MediaPlayer();
    private ArrayList<File> fileList = new ArrayList<File>();
    Button btnPause,btnForward,btnBackward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        //viewList =(ListView) findViewById(R.id.musicList);
        SD_PATH = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        getMusicList();
        Button btnUpdate=(Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fileList.size() == 0)
                    getMusicList();
            }
        });

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
                    mp.setDataSource(fileList.get(currentPosition).getPath());
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
        getfile(SD_PATH);
        ArrayAdapter<String> songList = new ArrayAdapter<String>(this, R.layout.web_view, songs);
        setListAdapter(songList);
    }
    public ArrayList<File> getfile(File dir) {

        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {

                if (listFile[i].isDirectory())
                 getfile(listFile[i]);
                else {
                    if (listFile[i].getPath().endsWith(".mp3")) {
                        fileList.add(listFile[i]);
                        songs.add(listFile[i].getName());
                    }
                }

            }
        }
        return fileList;
    }
    private int currentPosition=0;

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onActionModeFinished(ActionMode mode) {
        super.onActionModeFinished(mode);
    }

    private void playPrev()
    {
        if(currentPosition>0)
        {
            try {
                mp.reset();
                mp.setDataSource(fileList.get(--currentPosition).getPath());
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
            mp.setDataSource(fileList.get(++currentPosition).getPath());
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
            mp.setDataSource(fileList.get(currentPosition).getPath());
            mp.prepare();
            mp.start();
        }catch(IOException e){
            e.getStackTrace();
        }
    }

    @Override
    public void start() {
        mp.start();
    }

    @Override
    public void pause() {
        mp.pause();
    }

    @Override
    public int getDuration() {
        return mp.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return mp.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
mp.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }
}
