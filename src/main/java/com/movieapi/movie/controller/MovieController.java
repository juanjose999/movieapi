package com.movieapi.movie.controller;

import com.movieapi.movie.model.Movie;
import com.movieapi.movie.service.MovieService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        return new ResponseEntity<List<Movie>>(movieService.AllMovies(), HttpStatus.OK);
    }

    @GetMapping("/{ImdbId}")
    public ResponseEntity<Optional<Movie>> getSingleMovie(@PathVariable String ImdbId){
        return new ResponseEntity<Optional<Movie>>(movieService.singleMovie(ImdbId), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Movie>> getSingleMovieById(@PathVariable ObjectId idMovie){
        return new ResponseEntity<Optional<Movie>>(movieService.getSingleMovieById(idMovie), HttpStatus.OK);
    }
    @GetMapping("/title/{title}")
    public ResponseEntity<Movie> findByTitle(@PathVariable String title) {
        Optional<Movie> optionalMovie = movieService.findByTitle(title);
        return optionalMovie.map(movie -> new ResponseEntity<>(movie, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public Movie saveNewMovie(Movie movie){
        return movieService.saveNewMovie(movie);
    }

    @PutMapping
    public ResponseEntity<Movie> update(@RequestBody Movie idMovie) {
        Movie updatedMovie = movieService.update(idMovie);
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }


    @DeleteMapping("/id")
    public void delete(@PathVariable Movie idMovie){
        movieService.delete(idMovie);
    }
}
