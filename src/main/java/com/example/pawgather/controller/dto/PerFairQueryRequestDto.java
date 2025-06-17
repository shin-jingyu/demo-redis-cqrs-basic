package com.example.pawgather.controller.dto;

public final class PerFairQueryRequestDto {

    public record PetFairSearchList(
            String pageNumber,
            String keyword,
            String sort,
            String cursor
    ) {}

    public record PetFairSearchDate(
            String date,
            String zone
    ) {}
}
