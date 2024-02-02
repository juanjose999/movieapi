package com.movieapi.movie.MovieExeception;

public class MovieNotFoundExeception extends RuntimeException {
    public MovieNotFoundExeception(String message){
        super(message);
    }
}
