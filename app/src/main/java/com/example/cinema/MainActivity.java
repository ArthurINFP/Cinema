package com.example.cinema;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cinema.Movies.Movie;
import com.example.cinema.Movies.MovieInit;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView horrorRecyclerView;
    private MovieAdapter adapter;
    private ArrayList<Movie> moviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        horrorRecyclerView = findViewById(R.id.recycler_view_horror);

        // Initialize your movie list
        MovieInit movieInit = new MovieInit(this);
        moviesList = movieInit.movieInit();

        adapter = new MovieAdapter(this, moviesList);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_horror);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        horrorRecyclerView.setAdapter(adapter);
    }
}
