package com.example.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    // Usually involves inflating layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("Movie Adapter","onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        return new ViewHolder(movieView);
    }

    // Involves populating data into item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("Movie Adapter","onBindViewHolder" + position);
        // Get movie at passed in position
        Movie movie = movies.get(position);

        // Bind movie data into ViewHolder
        holder.bind(movie);
    }

    // Returns total count of items in list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewPoster;
        TextView textViewTitle;
        TextView textViewOverview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewOverview = itemView.findViewById(R.id.textViewOverview);

        }

        public void bind(Movie movie) {
            textViewOverview.setText(movie.getOverview());
            textViewTitle.setText(movie.getTitle());
            String imageURL;

            // if phone is in landscape
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                // then imageURL = back drop image
                imageURL = movie.getBackdropPath();
            } else{
                // else imageURL = poster image
                imageURL = movie.getPosterPath();
            }
            Glide.with(context).load(imageURL).into(imageViewPoster);
        }
    }
}
