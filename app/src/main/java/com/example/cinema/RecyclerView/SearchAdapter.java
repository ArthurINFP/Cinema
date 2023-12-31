package com.example.cinema.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cinema.Fragment.MovieFragment;
import com.example.cinema.MainActivity;
import com.example.cinema.Movies.Movie;
import com.example.cinema.Movies.MovieManager;
import com.example.cinema.R;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    Activity context;
    LayoutInflater inflater;
    ArrayList<Movie> data;

    public SearchAdapter(Activity context, ArrayList<Movie> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchAdapter.ViewHolder(inflater.inflate(R.layout.item_row_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        holder.update(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void filterMovie(ArrayList<Movie> filteredMovieList) {
        data = filteredMovieList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mvThumbnail, mvFavorite;
        TextView mvName, mvCategory, mvDuration, mvReleaseDate, mvTicketPrice;
        RatingBar mvRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mvThumbnail = itemView.findViewById(R.id.iv_thumbnail);
            mvFavorite = itemView.findViewById(R.id.iv_favorite);
            mvName = itemView.findViewById(R.id.tv_name);
            mvCategory = itemView.findViewById(R.id.tv_category);
            mvDuration = itemView.findViewById(R.id.tv_duration);
            mvReleaseDate = itemView.findViewById(R.id.tv_release_date);
            mvTicketPrice = itemView.findViewById(R.id.tv_price);
            mvRating = itemView.findViewById(R.id.rb_rating);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Movie movie = data.get(getAdapterPosition());
                    if (movie.isFavorite()) {
                        mvFavorite.setImageDrawable(context.getDrawable(R.drawable.ic_favorite_false));
                        setMovieFavorite(movie, false);
                    } else {
                        mvFavorite.setImageDrawable(context.getDrawable(R.drawable.ic_favorite_true));
                        setMovieFavorite(movie, true);
                    }
                    return false;

                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MovieFragment movieFragment = MovieFragment.newInstance(data.get(getAdapterPosition()));
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_display, movieFragment).addToBackStack(null).commit();
                }
            });
            mvFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Movie movie = data.get(getAdapterPosition());
                    if (movie.isFavorite()) {
                        mvFavorite.setImageDrawable(context.getDrawable(R.drawable.ic_favorite_false));
                        setMovieFavorite(movie, false);
                    } else {
                        mvFavorite.setImageDrawable(context.getDrawable(R.drawable.ic_favorite_true));
                        setMovieFavorite(movie, true);
                    }
                }
            });
        }


        private void setMovieFavorite(Movie movie, boolean b) {
            for (Movie m : MovieManager.getInstance().getMovies()) {  // <-- Updated here
                if (m.getTitle().equals(movie.getTitle())) {
                    m.setFavorite(b);
                    if (b) {
                        MovieManager.getInstance().addToFavorites(m);  // <-- Updated here
                    } else {
                        MovieManager.getInstance().removeFavoriteMovie(m);  // <-- Updated here
                    }
                    break;
                }
            }
        }


        public void update(Movie movie) {
            Glide.with(context).load(Base64.decode(movie.getBase64Image(), Base64.DEFAULT)).into(mvThumbnail);
            mvName.setText(movie.getTitle());
            mvCategory.setText(movie.getCategory());
            mvDuration.setText(Integer.toString(movie.getDuration()));
            mvReleaseDate.setText(movie.getReleaseDate());
            mvTicketPrice.setText(Float.toString(movie.getTicketPrice())+"đ");
            mvRating.setRating(movie.getRating());

            if (movie.isFavorite()) {
                mvFavorite.setImageDrawable(context.getDrawable(R.drawable.ic_favorite_true));
            } else {
                mvFavorite.setImageDrawable(context.getDrawable(R.drawable.ic_favorite_false));
            }
        }
    }
}
