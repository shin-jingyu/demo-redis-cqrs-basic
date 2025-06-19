package com.example.pawgather.repository;

import com.example.pawgather.controller.dto.PerFairQueryRequestDto.PetFairSearchList;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto;
import com.example.pawgather.domain.entity.PetFairRead;

import java.time.Instant;
import java.util.List;

public interface PetFairReadRepositoryCustom {
    List<PerFairQueryResponseDto.PetFairSummaryDto> findPetFairList(PetFairSearchList searchList);
    List<PetFairRead> findMainPagePoster();
    List<PerFairQueryResponseDto.PetFairSummaryDto> findMonthPetParis(Instant start, Instant end);
}
