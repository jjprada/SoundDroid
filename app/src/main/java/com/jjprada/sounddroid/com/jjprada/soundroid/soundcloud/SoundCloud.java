package com.jjprada.sounddroid.com.jjprada.soundroid.soundcloud;

import retrofit.RestAdapter;

/**
 * Created by Dr4ckO on 27/03/2015.
 */
public class SoundCloud {
    private static final String API_URL = "http://api.soundcloud.com";

    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder()
            //.setLogLevel(RestAdapter.LogLevel.FULL)
            .setEndpoint(API_URL)
            .build();

    private static final SoundCloudService SERVICE = REST_ADAPTER.create(SoundCloudService.class);

    public static SoundCloudService getService() {
        return SERVICE;
    }
}
