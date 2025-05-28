package com.example.pawgather.usecase;

import com.example.pawgather.controller.dto.PerFairQueryRequestDto.PetFairSearchList;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairSummaryDto;

import java.util.List;

public interface PetFairReadUseCase {
    List<PetFairSummaryDto> readPetFairs(PetFairSearchList petFairSearchList);
    PerFairQueryResponseDto.PetFairDetailDto readPetFairSummary(Long petFairId);
}
