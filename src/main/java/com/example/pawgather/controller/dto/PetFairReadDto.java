package com.example.pawgather.controller.dto;

import com.example.pawgather.domain.entity.PetFairStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetFairReadDto implements Serializable {
    private String title;
    private String posterImageUrl;
    private Instant startDate;
    private Instant endDate;
    private String simpleAddress;
    private String detailAddress;
    private String petFairUrl;
    private String content;
    private String mapUrl;
    private String telNumber;
    private PetFairStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private List<String> images;
}
