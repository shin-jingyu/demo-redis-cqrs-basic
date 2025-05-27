package com.example.pawgather.controller.dto;

import com.example.pawgather.domain.entity.PetFairStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

    public record PetFairSummaryDetailDto(
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
