package com.example.cinema.Movies;

import android.graphics.drawable.Drawable;

import com.example.cinema.MainActivity;

import java.time.LocalDate;
import java.util.ArrayList;

public class Movie {
    int id, duration;
    Drawable thumbnail;
    String title, description, category, trailerUrl, bookingUrl;
    float ticketPrice;
    ArrayList<String> comment;
    float rating;
    LocalDate releaseDate;


    // Constructor
    public Movie(int id, Drawable thumbnail, String trailerUrl, String bookingUrl,
                 String title, String description, float ticketPrice,
                 ArrayList<String> comment, String category,
                 int duration, float rating, LocalDate releaseDate) {
        this.id = id;
        this.thumbnail = thumbnail;
        this.duration = duration;
        this.trailerUrl = trailerUrl;
        this.bookingUrl = bookingUrl;
        this.title = title;
        this.description = description;
        this.ticketPrice = ticketPrice;
        this.comment = comment;
        this.category = category;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }

    // All setter and getter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Drawable getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Drawable thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public String getBookingUrl() {
        return bookingUrl;
    }

    public void setBookingUrl(String bookingUrl) {
        this.bookingUrl = bookingUrl;
    }

    public float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public ArrayList<String> getComment() {
        return comment;
    }

    public void setComment(ArrayList<String> comment) {
        this.comment = comment;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
