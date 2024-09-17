package dev.otee.robin.service;

import dev.otee.robin.model.Movie;
import dev.otee.robin.model.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private static final Logger log = LoggerFactory.getLogger(MovieService.class);

    @Autowired
    private MovieRepository movieRepository;

    public Optional<Movie> addMovie(Movie movie){
        movieRepository.save(movie);
        return Optional.of(movie);
    }

    public Double getAverageRating(String slug){
        List<Movie> movies = movieRepository.findBySlug(slug);
        if(movies.isEmpty()){
            return 0.0D;
        }
        return movies.getFirst().getAvgRating();
    }

    public Boolean movieExists(String slug){
        List<Movie> movies = movieRepository.findBySlug(slug);
        return !movies.isEmpty();
    }
}
