package com.example.pawgather.controller;

import com.example.pawgather.controller.dto.PerFairQueryRequestDto.PetFairSearchDate;
import com.example.pawgather.controller.dto.PerFairQueryRequestDto.PetFairSearchList;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairPosterSelectLimitDto;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairSummaryDto;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairDetailDto;
import com.example.pawgather.controller.dto.PetFairReadDto;
import com.example.pawgather.usecase.PetFairReadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PetFairQueryApi {

    private final PetFairReadUseCase petFairReadUseCase;

    @GetMapping("/v1/petfairs")
    public List<PetFairSummaryDto> readAllPetFairs(PetFairSearchList petFairSearchList) {
        return petFairReadUseCase.readPetFairs(petFairSearchList);
    }

    @GetMapping("/v1/petfairs/{id}")
    public PetFairReadDto readDetailPetFair(@PathVariable("id") String id) {
        return petFairReadUseCase.readPetFairSummary(id);
    }

    @GetMapping("/v1/home")
    public List<PetFairPosterSelectLimitDto> readLimitPetFairPoster() {
        return petFairReadUseCase.readLimitPetFairPoster();
    }

    @GetMapping("/v1/calendar")
    public List<PetFairSummaryDto> readMonthPetFairs(PetFairSearchDate searchDate) {
        return petFairReadUseCase.readMonthPetFairs(searchDate);
    }
}
