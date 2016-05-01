package com.example.unique.myapplication.view;


import android.content.Context;
import android.util.Log;

import com.example.unique.myapplication.R;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tahir De on 4/17/2016.
 */
public class YoutubeConnector {


    private static final long NUMBER_OF_VIDEOS_RETURNED = 10;

    private YouTube youtube;
    private YouTube.Search.List query;


    public static final String KEY = "AIzaSyB2pnabtBp1eos2-7-xot28bDXmOCQpv_8";//"AIzaSyBnpIsO3ns9HmRFZH4EQyK3YlnZsYuwMDE";

        public YoutubeConnector(Context context) {
            youtube = new YouTube.Builder(new NetHttpTransport(),
                    new JacksonFactory(), new HttpRequestInitializer() {
                @Override
                public void initialize(HttpRequest hr) throws IOException {}
            }).setApplicationName(context.getString(R.string.app_name)).build();

        try{
            query = youtube.search().list("id,snippet");
            query.setKey(KEY);
            query.setType("video");
            query.setFields("items(id/videoId,snippet/title,snippet/description,snippet/thumbnails/default/url)");
            query.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
        }catch(IOException e){
            Log.d("YC", "Hahahaha.........Could not initialize: "+e);
        }
    }

    public ArrayList<VideoItem> search(String keywords){

        query.setQ(keywords);

        try{

            SearchListResponse response = query.execute();
            List<SearchResult> results = response.getItems();

            ArrayList<VideoItem> items = new ArrayList<VideoItem>();
            for(SearchResult result:results){
                VideoItem item = new VideoItem();
                item.setTitle(result.getSnippet().getTitle());
                item.setDescription(result.getSnippet().getDescription());
                item.setThumbnailURL(result.getSnippet().getThumbnails().getDefault().getUrl());
                item.setId(result.getId().getVideoId());
                items.add(item);
            }
            return items;
        }catch(IOException e){
            Log.d("YC", "Hahahaha......Could not search: "+e);
            return null;
        }
    }

}