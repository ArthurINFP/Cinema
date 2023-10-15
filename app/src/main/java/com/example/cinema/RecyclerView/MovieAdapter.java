package com.example.cinema.RecyclerView;


import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.MainActivity;
import com.example.cinema.Movies.Movie;
import com.example.cinema.Movies.MovieManager;
import com.example.cinema.R;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private Context context;
    private ArrayList<Movie> movieList;
    private ArrayList<Movie> interestList = new ArrayList<>();

    public MovieAdapter(Context context, ArrayList<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        holder.title.setText(movie.getTitle());
        holder.category.setText(movie.getCategory());
        holder.duration.setText(String.valueOf(movie.getDuration()));
        holder.thumbnail.setImageDrawable(movie.getThumbnail());
        holder.rating.setText(String.valueOf(movie.getRating()));
        holder.releaseDate.setText(String.valueOf(movie.getReleaseDate()));

        if (movie.isFavorite()) {
            holder.addToInterest.setTextColor(Color.RED);
        } else {
            holder.addToInterest.setTextColor(Color.WHITE);
        }


        holder.addToInterest.setOnClickListener(v -> {
            if (!MovieManager.getInstance().getFavoriteMovies().contains(movie)) {
                MovieManager.getInstance().addToFavorites(movie);
                movie.setFavorite(true);
                Toast.makeText(context, "Added to favorites!", Toast.LENGTH_SHORT).show();
                holder.addToInterest.setTextColor(Color.RED);

            } else {
                MovieManager.getInstance().removeFavoriteMovie(movie);
                movie.setFavorite(false);
                Toast.makeText(context, "Removed from favorites!", Toast.LENGTH_SHORT).show();
                holder.addToInterest.setTextColor(Color.WHITE);
            }
            // Notify the adapter about the change
            notifyDataSetChanged();
        });

        holder.bookNow.setOnClickListener(v -> {
            // Your logic to book a movie
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}