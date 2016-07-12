package com.example.hungo.jsonn.until;

import android.util.Log;

import com.example.hungo.jsonn.model.VideoItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by hungo on 7/12/2016.
 */
public class ParseJson {
    public static ArrayList<VideoItem> processData(String dataString){
        ArrayList<VideoItem> lstData = new ArrayList<VideoItem>();

        try {
            JSONObject rootObject = new JSONObject(dataString);
            JSONArray jsArItems = rootObject.getJSONArray("items");
            for(int i =0 ; i < jsArItems.length() ; i ++){
                JSONObject jsOjItem = jsArItems.getJSONObject(i);
                JSONObject jsOjSnippet = jsOjItem.getJSONObject("snippet");
                String title = jsOjSnippet.getString("title");
                Log.d("Title", title);
                String publishedAt = jsOjSnippet.getString("publishedAt");
                Log.d("publishedAt",  publishedAt);
                String description = jsOjSnippet.getString("description");
                Log.d("description" , description);

                JSONObject jsOjThumbnails = jsOjSnippet.getJSONObject("thumbnails");
                JSONObject jsOjHigh = jsOjThumbnails.getJSONObject("high");
                String url = jsOjHigh.getString("url");
                Log.d("URL" , url);

                VideoItem videoItem = new VideoItem(title,description, publishedAt, url);
                lstData.add(videoItem);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lstData;
    }
}
