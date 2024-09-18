package dev.otee.robin.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;

@Table("ratings")
public class Rating {
    @Id
    Integer id;
    Integer userId;
    Integer movieId;
    Double rating;
    OffsetDateTime createdAt;

    public Rating(Integer userId, Integer movieId, Double rating){
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.createdAt = OffsetDateTime.now();
    }
}
