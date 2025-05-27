package com.example.pawgather.controller.dto;

import java.time.Instant;

public final class PerFairQueryResponseDto {

    public record PetFairSummaryDto(
             Long petFairId,
             String title,
             String posterImageUrl,
             Instant startDate,
             Instant endDate,
             String simpleAddress
    ) {}
}
