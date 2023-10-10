package com.example.cinema.Movies;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.appcompat.content.res.AppCompatResources;

import com.example.cinema.R;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MovieInit {
    Context context;

    public MovieInit(Context context) {
        this.context = context;
    }

    public ArrayList<Movie> movieInit() {
        ArrayList<Movie> movieList = new ArrayList<>();

        // Collect data from resource strings.xml
        String[] titleArray = context.getResources().getStringArray(R.array.movie_title);
        String[] trailerArray = context.getResources().getStringArray(R.array.movie_trailer);
        String[] descriptionArray = context.getResources().getStringArray(R.array.movie_description);
        String[] bookingArray = context.getResources().getStringArray(R.array.movie_booking);
        String[] categoryArray = context.getResources().getStringArray(R.array.movie_category);
        int[] durationArray = context.getResources().getIntArray(R.array.movie_duration);
        int[] ticketPricesArray = context.getResources().getIntArray(R.array.movie_ticket_price);

        TypedArray ratingsTypedArray = context.getResources().obtainTypedArray(R.array.movie_rating);
        float[] ratingArray = new float[ratingsTypedArray.length()];
        for (int i = 0; i < ratingsTypedArray.length(); i++) {
            ratingArray[i] = ratingsTypedArray.getFloat(i, -1.0f); // -1.0f is a default value if there's a problem
        }
        ratingsTypedArray.recycle(); // Always recycle a TypedArray when done


        String[] releaseDateArray = context.getResources().getStringArray(R.array.movie_release_date);
        Drawable thumbnail = getImage(R.drawable.kumarn);

        int[] imageArray = {
                R.drawable.kumarn,
                R.drawable.kumarn,
                R.drawable.kumarn,
                R.drawable.kumarn,
                R.drawable.kumarn,
        };


        // Create a movie list through the provided data
        for (int i = 0; i < titleArray.length; i++) {
            Drawable currentThumbnail = getImage(imageArray[i]);
            movieList.add(new Movie(
                    0,
                    currentThumbnail,
                    trailerArray[i],
                    bookingArray[i],
                    titleArray[i],
                    descriptionArray[i],
                    ticketPricesArray[i], // Use actual ticket price if available
                    new ArrayList<String>(),
                    categoryArray[i],
                    durationArray[i],
                    ratingArray[i], // Use parsed float value here
                    LocalDate.parse(releaseDateArray[i])
            ));

        }

        return movieList;
    }

    public Drawable getImage(int id){
        return AppCompatResources.getDrawable(context, id);
    }
}
