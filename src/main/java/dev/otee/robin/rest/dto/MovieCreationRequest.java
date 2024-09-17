package dev.otee.robin.rest.dto;

public record MovieCreationRequest(
        String name,
        String description,
        String[] tags,
        Integer releasedYear,
        String language
) {}
