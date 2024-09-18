package dev.otee.robin.model;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Integer> {

    List<Movie> findBySlug(String slug);

    @Query("UPDATE movies SET avg_rating = :rating WHERE slug = :slug RETURNING *")
    List<Movie> updateAvgRating(@Param("rating") Double rating, @Param("slug") String slug);
}