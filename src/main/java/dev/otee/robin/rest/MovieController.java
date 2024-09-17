package dev.otee.robin.rest;

import dev.otee.robin.Utils;
import dev.otee.robin.model.Movie;
import dev.otee.robin.rest.dto.CreateMovieResponse;
import dev.otee.robin.rest.dto.ErrorResponse;
import dev.otee.robin.rest.dto.MovieCreationRequest;
import dev.otee.robin.rest.dto.Response;
import dev.otee.robin.service.MovieService;
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
}