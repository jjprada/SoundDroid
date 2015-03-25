package com.jjprada.sounddroid;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dr4ckO on 25/03/2015.
 */
public class Track {
    private int mID;
    private String mTitle;
    private String mStreamURL;

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getStreamURL() {
        return mStreamURL;
    }

    public void setStreamURL(String streamURL) {
        mStreamURL = streamURL;
    }

    public static Track parse(JSONObject jsonObject){
        Track t = new Track();
        try {
            t.setID((int)jsonObject.get("id"));
            t.setTitle(jsonObject.getString("title"));
            t.setStreamURL(jsonObject.getString("stream_url"));
        }catch (JSONException e){}

        return t;
    }
}
