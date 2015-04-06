package com.jjprada.sounddroid.com.jjprada.soundroid.soundcloud;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dr4ckO on 25/03/2015.
 */
public class Track {
    @SerializedName("id")
    private int mID;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("stream_url")
    private String mStreamURL;

    @SerializedName("artwork_url")
    private String mArtworkURL;

    public int getID() {
        return mID;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getStreamURL() {
        return mStreamURL;
    }

    public String getArtworkURL() {
        return mArtworkURL;
    }

    public String getAvatarURL(){
        String avatarURL = mArtworkURL;
        if (avatarURL != null){         // Chequeo de segurida para que no haya un "crash" si "mArtWorkURL" es null
            avatarURL = mArtworkURL.replace("large", "tiny");
        }
        return avatarURL;
    }
}
