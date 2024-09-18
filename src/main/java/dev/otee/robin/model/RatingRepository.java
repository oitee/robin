package dev.otee.robin.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RatingRepository extends CrudRepository<Rating, Integer> {
    List<Rating> findByUserIdAndMovieId(Integer userId, Integer movieId);
    List<Rating> findByMovieId(Integer movieId);
}
