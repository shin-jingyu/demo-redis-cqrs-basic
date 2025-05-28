package com.example.pawgather.controller.dto;

public final class PerFairQueryRequestDto {

    public record PetFairSearchList(
            String keyword,
            String sort,
            String cursor
    ) {}
}
