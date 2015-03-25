package com.jjprada.sounddroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(trackJSON());
        }catch (JSONException e){
            Log.d(TAG,"JSONException is: "+e);
        }
        Track track = Track.parse(jsonObject);
        Log.d(TAG,"This is the title: "+track.getTitle());
        Log.d(TAG,"This is the id: "+track.getID());
        Log.d(TAG,"This is the streamURL: "+track.getStreamURL());

    }

    private String trackJSON(){
        return "{\"kind\":\"track\",\"id\":13158665,\"created_at\":\"2011/04/06 15:37:43 +0000\",\"user_id\":3699101,\"duration\":18109,\"commentable\":true,\"state\":\"finished\",\"original_content_size\":201483,\"last_modified\":\"2013/02/18 19:18:11 +0000\",\"sharing\":\"public\",\"tag_list\":\"soundcloud:source=iphone-record\",\"permalink\":\"munching-at-tiannas-house\",\"streamable\":true,\"embeddable_by\":\"all\",\"downloadable\":false,\"purchase_url\":null,\"label_id\":null,\"purchase_title\":null,\"genre\":null,\"title\":\"Munching at Tiannas house\",\"description\":null,\"label_name\":null,\"release\":null,\"track_type\":\"recording\",\"key_signature\":null,\"isrc\":null,\"video_url\":null,\"bpm\":null,\"release_year\":null,\"release_month\":null,\"release_day\":null,\"original_format\":\"m4a\",\"license\":\"all-rights-reserved\",\"uri\":\"https://api.soundcloud.com/tracks/13158665\",\"user\":{\"id\":3699101,\"kind\":\"user\",\"permalink\":\"alex-stevenson\",\"username\":\"Alex Stevenson\",\"last_modified\":\"2011/06/13 23:58:44 +0000\",\"uri\":\"https://api.soundcloud.com/users/3699101\",\"permalink_url\":\"http://soundcloud.com/alex-stevenson\",\"avatar_url\":\"https://i1.sndcdn.com/avatars-000004193858-jnf2pd-large.jpg\"},\"created_with\":{\"id\":124,\"kind\":\"app\",\"name\":\"SoundCloud iOS\",\"uri\":\"https://api.soundcloud.com/apps/124\",\"permalink_url\":\"http://soundcloud.com/apps/iphone\",\"external_url\":\"http://itunes.com/app/soundcloud\"},\"permalink_url\":\"http://soundcloud.com/alex-stevenson/munching-at-tiannas-house\",\"artwork_url\":null,\"waveform_url\":\"https://w1.sndcdn.com/fxguEjG4ax6B_m.png\",\"stream_url\":\"https://api.soundcloud.com/tracks/13158665/stream\",\"playback_count\":5655,\"download_count\":134,\"favoritings_count\":2,\"comment_count\":3,\"attachments_uri\":\"https://api.soundcloud.com/tracks/13158665/attachments\",\"policy\":\"ALLOW\"}";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
