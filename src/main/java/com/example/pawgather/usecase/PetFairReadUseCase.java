package com.example.pawgather.usecase;

import com.example.pawgather.controller.dto.PerFairQueryRequestDto.PetFairSearchList;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairSummaryDetailDto;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairSummaryDto;

import java.util.List;

public interface PetFairReadUseCase {
    List<PetFairSummaryDto> readPetFairs(PetFairSearchList petFairSearchList);
    PetFairSummaryDetailDto readPetFairSummary(Long petFairId);
}
