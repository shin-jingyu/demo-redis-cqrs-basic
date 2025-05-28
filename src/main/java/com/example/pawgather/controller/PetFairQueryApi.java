package com.example.pawgather.controller;

import com.example.pawgather.controller.dto.PerFairQueryRequestDto.PetFairSearchList;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairSummaryDto;
import com.example.pawgather.controller.dto.PerFairQueryResponseDto.PetFairDetailDto;
import com.example.pawgather.usecase.PetFairReadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.pawgather.controller.dto.PetFairQueryDto.*;

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
    public PetFairDetailDto readDetailPetFair(@PathVariable("id") Long id) {
        return petFairReadUseCase.readPetFairSummary(id);
    }

    @GetMapping("/v1/home")
    public List<PetFairSelectLimitResponse> readPetFairPosterTop10() {
        return petFairReadUseCase.readLimitPetFairPoster();
}
