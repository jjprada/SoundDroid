package com.jjprada.sounddroid;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.jjprada.sounddroid.com.jjprada.soundroid.soundcloud.SoundCloud;
import com.jjprada.sounddroid.com.jjprada.soundroid.soundcloud.SoundCloudService;
import com.jjprada.sounddroid.com.jjprada.soundroid.soundcloud.Track;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity implements SearchView.OnQueryTextListener {

    private static final String TAG = "MainActivity";

    private TracksAdapter mAdapter;
    private List<Track> mTracks;
    private ImageView mPlayerThumbnail;
    private TextView mPlayerTitle;
    private ImageView mPlayerToggleButton;
    private MediaPlayer mMediaPlayer;
    private SoundCloudService mService;
    private SearchView mSearchView;
    private String mCheck = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar playerToolbar = (Toolbar)findViewById(R.id.player_toolbar);
        mPlayerThumbnail = (ImageView)findViewById(R.id.player_thumbnail_track);
        mPlayerTitle = (TextView)findViewById(R.id.player_title_track);
        mPlayerToggleButton = (ImageView)findViewById(R.id.player_toggle_button);
        mPlayerToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSongState();
            }
        });

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                toggleSongState();
            }
        });
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mPlayerToggleButton.setImageResource(R.drawable.ic_play);
            }
        });

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.sound_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTracks = new ArrayList<Track>();
        mAdapter = new TracksAdapter(this, mTracks);
        mAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Track selectedTrack = mTracks.get(position);

                Picasso.with(MainActivity.this).load(selectedTrack.getAvatarURL()).into(mPlayerThumbnail);
                mPlayerTitle.setText(selectedTrack.getTitle());

                if (mMediaPlayer.isPlaying()){
                    mMediaPlayer.stop();
                    mMediaPlayer.reset();
                }

                try {
                    mMediaPlayer.setDataSource(selectedTrack.getStreamURL() + "?client_id=" + SoundCloudService.CLIENT_ID);
                    mMediaPlayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        recyclerView.setAdapter(mAdapter);

        mService = SoundCloud.getService();
        updateTracks();
    }

    private void updateTracks() {
        String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        mService.getRecentSongs(date, getCallback());
    }

    private Callback<List<Track>> getCallback() {
        return new Callback<List<Track>>() {
            @Override
            public void success(List<Track> tracks, Response response) {
                mTracks.clear();
                mTracks.addAll(tracks);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) { }
        };
    }

    private void toggleSongState() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mPlayerToggleButton.setImageResource(R.drawable.ic_play);
        } else {
            mMediaPlayer.start();
            mPlayerToggleButton.setImageResource(R.drawable.ic_pause);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mMediaPlayer != null){
            if (mMediaPlayer.isPlaying()){
                mMediaPlayer.stop();
            }
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        mSearchView = (SearchView)menu.findItem(R.id.actions_search).getActionView();
        mSearchView.setOnQueryTextListener(this);
        MenuItemCompat.setOnActionExpandListener(menu.findItem(R.id.actions_search), new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                updateTracks();
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.actions_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (!mCheck.equals(query)){     // Comprueba que la busqueda no sea igual a la última busqueda hecha
            mService.searchSongs(query, getCallback());
            mCheck = query;
        }
        mSearchView.clearFocus();

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
