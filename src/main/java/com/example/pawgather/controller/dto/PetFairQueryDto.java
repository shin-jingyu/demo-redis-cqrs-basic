package com.example.pawgather.controller.dto;

import lombok.Builder;

public final class PetFairQueryDto {

    private PetFairQueryDto() {}

    @Builder
    public record PetFairSelectLimitResponse(
            Long id,
            String posterImageUrl
    ) {}
}
