package com.jjprada.sounddroid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjprada.sounddroid.com.jjprada.soundroid.soundcloud.Track;

import java.util.List;

/**
 * Created by Dr4ckO on 27/03/2015.
 */
public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final ImageView thumbImageView;

        ViewHolder(View v) {                // Constructor de "ViewHolder"
            super(v);
            titleTextView = (TextView)v.findViewById(R.id.track_title);         // Usamos esto solo una vez, porque consume mucho tiempo
            thumbImageView = (ImageView)v.findViewById(R.id.track_thumbnail);
        }
    }

    private List<Track> mTracks;

    TracksAdapter (List<Track> tracks){     // Constructor de "TracksAdapter"
        mTracks = tracks;
    }

    @Override
    public int getItemCount() {
        return mTracks.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.track_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Track track = mTracks.get(position);
        holder.titleTextView.setText(track.getTitle());
        holder.thumbImageView.setImageResource();
    }


}
