package com.example.pawgather.usecase;

import com.example.pawgather.controller.dto.PerFairQueryRequestDto.PetFairSearchDate;
import com.example.pawgather.controller.dto.PerFairQueryRequestDto.PetFairSearchList;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairDetailDto;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairPosterSelectLimitDto;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairSummaryDto;

import java.util.List;

public interface PetFairReadUseCase {
    List<PetFairSummaryDto> readPetFairs(PetFairSearchList petFairSearchList);
    PerFairQueryResponseDto.PetFairDetailDto readPetFairSummary(Long petFairId);
    List<PetFairPosterSelectLimitDto> readLimitPetFairPoster();
    List<PetFairDetailDto> readMonthPetFairs(PetFairSearchDate petFairSearchDate);
}
