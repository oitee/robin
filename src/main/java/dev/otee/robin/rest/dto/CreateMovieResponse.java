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
    public CreateMovieResponse(Movie createdMovie) {
        this(createdMovie.getId(),
                createdMovie.getSlug(),
                createdMovie.getName(),
                createdMovie.getAvgRating(),
                createdMovie.getDescription(),
                createdMovie.getTags().split(","),
                createdMovie.getReleasedYear(),
                createdMovie.getLang(),
                createdMovie.getCreatedAt());
    }
}
