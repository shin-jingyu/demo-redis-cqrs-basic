package com.example.pawgather.controller.dto;

import com.example.pawgather.domain.entity.PetFairStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.List;

public final class PerFairQueryResponseDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record PetFairSummaryDto(
             String petFairId,
             String title,
             String posterImageUrl,
             Instant startDate,
             Instant endDate,
             String simpleAddress,
             Integer pageNum
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

    public record PetFairPosterSelectLimitDto(
            String id,
            String posterImageUrl
    ) {}
}
