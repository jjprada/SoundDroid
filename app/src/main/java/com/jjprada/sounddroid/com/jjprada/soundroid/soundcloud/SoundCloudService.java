package com.jjprada.sounddroid.com.jjprada.soundroid.soundcloud;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Dr4ckO on 26/03/2015.
 */
public interface SoundCloudService {

    static final String CLIENT_ID = "ee2445801ff5b449eac7c77a6c65c3e4";

    @GET("/tracks?client_id="+CLIENT_ID)
    public void searchSongs(@Query("q") String query, Callback<List<Track>> cb);
}
