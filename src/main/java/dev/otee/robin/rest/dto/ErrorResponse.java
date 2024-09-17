package dev.otee.robin.rest.dto;

public record ErrorResponse(
        String error
) implements Response {
}
