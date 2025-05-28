package com.example.pawgather.controller.dto;

import com.example.pawgather.domain.entity.PetFairStatus;

import java.time.Instant;
import java.util.List;

public final class PerFairQueryResponseDto {

    public record PetFairSummaryDto(
             Long petFairId,
             String title,
             String posterImageUrl,
             Instant startDate,
             Instant endDate,
             String simpleAddress
    ) {}

    public record PetFairDetailDto(
            Long petFairId,
            String title,
            String posterImageUrl,
            Instant startDate,
            Instant endDate,
            String simpleAddress,
            String detailAddress,
            String petFairUrl,
            String content,
            String mapUrl,
            String telNumber,
            PetFairStatus status,
            Instant createdAt,
            Instant updatedAt,
            List<String> images
    ) {}
}
