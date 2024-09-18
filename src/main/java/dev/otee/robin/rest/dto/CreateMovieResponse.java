package dev.otee.robin.rest.dto;

import dev.otee.robin.model.Movie;

import java.time.OffsetDateTime;

public record CreateMovieResponse(
        Integer id,
        String slug,
        String name,
        Double rating,
        String description,
        String[] tags,
        Integer releasedYear,
        String language,
        OffsetDateTime createdAt
) implements Response {
    public CreateMovieResponse(Movie movie) {
        this(movie.getId(),
                movie.getSlug(),
                movie.getName(),
                movie.getAvgRating(),
                movie.getDescription(),
                movie.getTags().split(","),
                movie.getReleasedYear(),
                movie.getLang(),
                movie.getCreatedAt());
    }
}
