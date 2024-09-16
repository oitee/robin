package dev.otee.robin.rest.dto;

public record CreateUserResponse(
        String username,
        Integer id
        ) implements Response{
}
