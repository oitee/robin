package dev.otee.robin.service;

import dev.otee.robin.model.Movie;
import dev.otee.robin.model.MovieRepository;
import dev.otee.robin.model.Rating;
import dev.otee.robin.model.RatingRepository;
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

    @Autowired
    private RatingRepository ratingRepository;

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

    public Optional<Movie> getMovie(String slug){
        List<Movie> movies = movieRepository.findBySlug(slug);
        if (movies.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(movies.getFirst());
    }

    public Boolean ratingExists(Integer userId, Integer movieId){
        List<Rating> rating = ratingRepository.findByUserIdAndMovieId(userId, movieId);
        return !rating.isEmpty();
    }

    public Movie addRating(Integer userId, Movie movie, Double userRating){
        List<Rating> ratings = ratingRepository.findByMovieId(movie.getId());
        int totalReviews = ratings.size();

        Double newAvgRating;
        newAvgRating = (movie.getAvgRating() + userRating) / (totalReviews + 1);

        Movie updatedMovie = movieRepository.updateAvgRating(newAvgRating, movie.getSlug()).getFirst();
        Rating rating = new Rating(userId, movie.getId(), userRating);
        ratingRepository.save(rating);
        return updatedMovie;
    }
}
