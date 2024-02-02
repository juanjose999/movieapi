package com.movieapi.movie.service;

import com.movieapi.movie.MovieExeception.MovieNotFoundExeception;
import com.movieapi.movie.model.Movie;
import com.movieapi.movie.repository.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;


import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    public List<Movie> AllMovies(){
        return movieRepository.findAll();
    }

    public Optional<Movie> singleMovie(String ImdbId){
        return movieRepository.findMovieByImdbId(ImdbId);
    }
    public Optional<Movie> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }
    public Optional<Movie> getSingleMovieById(ObjectId id){
        return movieRepository.getSingleMovieById(id);
    }
    public Movie saveNewMovie(Movie movie){
        return movieRepository.save(movie);
    }
    public Movie update(Movie idMovie){
        Optional<Movie> existingMovieOptional = movieRepository.findById(idMovie.getId());
        if(existingMovieOptional.isPresent()){
            //actualizar campos
            Movie existingMovie = existingMovieOptional.get();
            existingMovie.setImdbId(idMovie.getImdbId());
            existingMovie.setTitle(idMovie.getTitle());
            existingMovie.setReleaseDate(idMovie.getReleaseDate());
            existingMovie.setTrailerLink(idMovie.getTrailerLink());
            existingMovie.setPoster(idMovie.getPoster());
            existingMovie.setGenres(idMovie.getGenres());
            existingMovie.setBackdrops(idMovie.getBackdrops());
            existingMovie.setReviewIds(idMovie.getReviewIds());

            return movieRepository.save(existingMovie);

        }else {
            throw new MovieNotFoundExeception("Movie not found with ID: " + idMovie.getId());
        }
    }
    public void delete(Movie idMovie){
        movieRepository.delete(idMovie);
    }

}
