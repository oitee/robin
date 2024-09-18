package dev.otee.robin.rest.dto;

public record SetRatingRequest(
        String username,
        Double rating
) {
}
