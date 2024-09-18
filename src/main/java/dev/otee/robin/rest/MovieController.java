package dev.otee.robin.rest;

import dev.otee.robin.Utils;
import dev.otee.robin.model.Movie;
import dev.otee.robin.model.Users;
import dev.otee.robin.rest.dto.*;
import dev.otee.robin.service.MovieService;
import dev.otee.robin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MovieController {
    private static final Logger log = LoggerFactory.getLogger(MovieController.class);
    @Autowired
    private MovieService service;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }

    @PostMapping("/movie")
    @ResponseBody
    public ResponseEntity<Response> createMovie(@RequestBody MovieCreationRequest movie){
        String title = movie.name();
        String slug = Utils.generateSlug(title);
        String tags = String.join(",", movie.tags());
        if(service.movieExists(slug)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Movie already exists with slug: " + slug));
        }
        Movie newMovie = new Movie(title, slug, movie.description(), tags, movie.releasedYear(), movie.language());
        Optional<Movie> createdMovie = service.addMovie(newMovie);
        if(createdMovie.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Movie could not be added"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new CreateMovieResponse(createdMovie.get()));
    }

    @GetMapping("/movie/{slug}")
    @ResponseBody
    public ResponseEntity<Response> getMovie(@PathVariable String slug){
        Optional<Movie> movie = service.getMovie(slug);
        if (movie.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Movie not found"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new GenericMovieResponse(movie.get()));

    }

    @PostMapping("/movie/{slug}/rate")
    @ResponseBody
    public ResponseEntity<Response> setRating(@PathVariable String slug, @RequestBody SetRatingRequest ratingRequest){
        Optional<Movie> movie = service.getMovie(slug);
        if (movie.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No movie by that slug exists"));
        }
        Optional<Users> user = userService.getUser(ratingRequest.username());
        if (user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No user by that username present"));
        }
        Boolean ratingExists = service.ratingExists(user.get().getId(), movie.get().getId());
        if(ratingExists){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("This user already has a rating for this movie"));
        }
        service.addRating(user.get().getId(), movie.get(), ratingRequest.rating());
        return ResponseEntity.status(HttpStatus.OK).body(new GenericMovieResponse(movie.get()));
    }
}