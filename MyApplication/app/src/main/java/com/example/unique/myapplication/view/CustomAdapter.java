package com.example.unique.myapplication.view;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.unique.myapplication.R;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tahir De on 4/20/2016.
 */
public class CustomAdapter extends BaseAdapter implements ListAdapter {

    private Context context;

    private ArrayList<VideoItem> Videos=new ArrayList<VideoItem>();
    private Handler handler;

    public CustomAdapter(ArrayList<VideoItem> li,Context context) {
      try {
          this.context = context;
          this.Videos = li;
      }catch (Exception e)
      {
          e.getStackTrace();
      }
    }

    @Override
    public int getCount() throws NullPointerException{
        try {
            int num = Videos.size();

            return num;
        }catch (NullPointerException e)
        {
            e.getStackTrace();
        return 0;
        }
    }

    @Override
    public Object getItem(int i) {
        return Videos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.video_item, null);
        }
        ImageView thumbnail= (ImageView) v.findViewById(R.id.video_thumbnail);
        TextView title = (TextView) v.findViewById(R.id.video_title);
        TextView des = (TextView) v.findViewById(R.id.video_description);
        title.setText(Videos.get(i).getTitle());
        des.setText(Videos.get(i).getDescription());
        Picasso.with(this.context.getApplicationContext()).load(Videos.get(i).getThumbnailURL()).into(thumbnail);
        return v;

    }

}
