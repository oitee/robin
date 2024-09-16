package dev.otee.robin.rest.dto;

public record UserExistsResponse(
        Boolean exists
) implements Response{
}
